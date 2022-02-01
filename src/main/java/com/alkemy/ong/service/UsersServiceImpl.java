package com.alkemy.ong.service;

import com.alkemy.ong.dto.LoginUsersDTO;

import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.mapper.UsersMapper;
import com.alkemy.ong.repository.UsersRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.util.JWT;
import java.text.MessageFormat;
import static com.alkemy.ong.util.Constants.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UsersService {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found: {0}";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWT jwt;
    @Autowired 
	private EmailServiceImp sendGridEmailService;

    public UsersDtoResponse login(LoginUsersDTO loginUser) throws Exception {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
            );
            Optional<Users> users = usersRepository.findByEmail(auth.getName());
            if (users.isPresent()) {
                Users user = users.get();
                if (user.isActive()) 
                {
                    return userToken(users.get());
                } else {
                    throw new Exception("Unsubscribed user");
                }
            } else {
                throw new BadCredentialsException("Username does not exist");
            }
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Username does not exist");
        } catch (InternalAuthenticationServiceException e) {
            throw new InternalAuthenticationServiceException("Username does not exist");
        }
    }

    @Override
    public Optional<Users> findByMail(String email) {
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isPresent()) {
            Users user = users.get();
            if (user.isActive() == true) {
                return usersRepository.findByEmail(email);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public UsersDtoResponse save(NewUsersDTO userDTO) {
        Optional<Users> user = usersRepository.findByEmail(userDTO.getEmail());

        if (user.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Users userModel = usersMapper.newUsersDTO2Model(userDTO);
        Users userSaved = usersRepository.save(userModel);
        this.sendGridEmailService.sendWelcomeEmail(MAIL_ONG, userDTO.getEmail()); 
        UsersDtoResponse response = (UsersDtoResponse) usersMapper.usersModel2UsersDtoResponse(userSaved);
        return response;
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Users> user = usersRepository.findById(id);
        if (user.isPresent()) {
            user.get().setActive(false);
            usersRepository.save(user.get());
        } else {
            throw new Exception("user not found");
        }
    }

    @Override
    public Users select(Long id) {
        return usersRepository.getById(id);
    }

    public UsersOkDto getUserOkDto(String email) {
        return usersMapper.userOkDtoToUser(usersRepository.findByEmail(email).get());
    }

    @Override
    public Users save(UsersRegisterDTO usersRegisterDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsersDtoResponse getUserDetails(String authHeader) {
        String userEmail = jwt.extractUserEmail(authHeader);
        Optional<Users> userEntity = usersRepository.findByEmail(userEmail);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format(USER_NOT_FOUND_ERROR_MESSAGE, userEmail));
        }
        UsersDtoResponse userResponse = new UsersDtoResponse();
        userResponse.setEmail(userEntity.get().getEmail());
        userResponse.setFirstName(userEntity.get().getFirstName());
        userResponse.setLastName(userEntity.get().getLastName());
        return userResponse;
    }

    @Transactional
    public UsersDtoResponse update(Long id, NewUsersDTO dto) {

        Optional<Users> result = usersRepository.findById(id);
        if (result.isEmpty() || !result.get().isActive())
            return null;

        Users entity = result.get();
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        Users entityUpdated = usersRepository.save(entity);
        return usersMapper.usersModel2UsersDtoResponse(entityUpdated);
    }

    private UsersDtoResponse userToken(Users user) {
        UsersDtoResponse tokenUser = usersMapper.usersModel2UsersDtoResponse(user);
        tokenUser.setRole(user.getRole());
        tokenUser.setToken(jwt.generateToken(tokenUser));
        System.out.println(tokenUser.getToken());
        return tokenUser;
    }

}
