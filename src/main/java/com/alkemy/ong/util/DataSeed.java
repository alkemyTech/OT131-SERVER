package com.alkemy.ong.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import com.alkemy.ong.model.Activities;
import com.alkemy.ong.model.Organizations;
import com.alkemy.ong.model.Roles;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.ActivitiesRepository;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.repository.RolesRepository;
import com.alkemy.ong.repository.UsersRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@Profile("!test")
@Component
public class DataSeed implements CommandLineRunner {

    @Autowired
    private ActivitiesRepository activitiesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private OrganizationsRepository organizationsRepository;

    @Autowired
    private ReadScripts readScript;
    @Value("classpath:scripts/usersScript.sql")
    private Resource usersScript;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadOrganizationsData();
        loadActivitiesData();
    }

    private void loadUserData() throws IOException {

        if (usersRepository.count() == 0) {

            Users admin = new Users(
                    "Admin", "Admin",
                    "admin@ong.com", "12345678"
            );
            Roles roleAdmin = new Roles(
                    RoleName.ROLE_ADMIN, "Admin");

            rolesRepository.save(roleAdmin);
            admin.setRole(roleAdmin);
            usersRepository.save(admin);

            Users user = new Users(
                    "User", "User",
                    "user@ong.com", "12345678"
            );
            Roles roleUser = new Roles(
                    RoleName.ROLE_USER, "User"
            );

            rolesRepository.save(roleUser);
            user.setRole(roleUser);
            usersRepository.save(user);

            readScript.dataSourceInitializer(usersScript);
            readScript.encodePasswordPopulator();

        }
    }

    private void loadOrganizationsData() {

        if (organizationsRepository.count() == 0) {
            Organizations organization = Organizations.builder()
                    .name("SomosMás")
                    .aboutUsText("Desde 1997 en Somos Más trabajamos con los chicos y chicas, mamás y papás, abuelos y vecinos del barrio La Cava generando procesos de crecimiento y de inserción social. Uniendo las manos de todas las familias, las que viven en el barrio y las que viven fuera de él, es que podemos pensar, crear y garantizar estos procesos. Somos una asociación civil sin fines de lucro que se creó en 1997 con la intención de dar alimento a las familias del barrio. Con el tiempo fuimos involucrándonos con la comunidad y agrandando y mejorando nuestra capacidad de trabajo. Hoy somos un centro comunitario que acompaña a más de 700 personas a través de las áreas de: educación, deportes, primera infancia, salud, alimentación y trabajo social")
                    .phone(1160112988)
                    .email("somosfundacionmas@gmail.com")
                    .isActive(true)
                    .createdDate(LocalDate.now())
                    .images("images")
                    .welcomeText("welcomeText")
                    .build();

            organizationsRepository.save(organization);

        }

    }

    private void loadActivitiesData() {

        if (activitiesRepository.count() == 0) {
            Activities activity = Activities.builder()
                    .name("Programas educativos")
                    .content("Buscamos incrementar la capacidad intelectual, moral y afectiva de las personas de acuerdo con la cultura y las normas de convivencia de la sociedad a la que pertenecen.")
                    .image("image")
                    .isActive(true)
                    .build();
            activitiesRepository.save(activity);

            Activities activity2 = Activities.builder()
                    .name("Apoyo escolar para nivel primario")
                    .content("Este taller está pensado para ayudar a los alumnos con el material que traen de la escuela, también tenemos una docente que les da  clases de lengua y matemática con una planificación propia para nivelar que vayan con más herramientas a la escuela")
                    .image("image")
                    .isActive(true)
                    .build();
            activitiesRepository.save(activity2);

            Activities activity3 = Activities.builder()
                    .name("Apoyo escolar para nivel secundario")
                    .content("Aquí los jóvenes se presentan con el material que traen del colegio y una docente de la institución y un grupo de voluntarios los recibe para ayudarlos a estudiar o hacer la tarea.También es utilizado como un punto de relación entre ellos y la institución")
                    .image("image")
                    .isActive(true)
                    .build();
            activitiesRepository.save(activity3);

            Activities activity4 = Activities.builder()
                    .name("Tutorias")
                    .content("El objetivo de esta propuesta es lograr la integración escolar de niños y jóvenes del barrio promoviendo el soporte socioeducativo y emocional apropiado, desarrollando los recursos institucionales necesarios a través de la articulación de nuestras intervenciones con las escuelas que los alojan, con las familias de los alumnos y con ellas instancias municipales, provinciales y nacionales que correspondan.")
                    .image("image")
                    .isActive(true)
                    .build();
            activitiesRepository.save(activity4);

        }

    }
}
