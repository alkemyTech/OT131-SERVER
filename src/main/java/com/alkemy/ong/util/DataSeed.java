package com.alkemy.ong.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.alkemy.ong.model.Activities;
import com.alkemy.ong.repository.ActivitiesRepository;


@Component
public class DataSeed implements CommandLineRunner

{
	@Autowired
	private ActivitiesRepository activitiesRepository;

	@Override
	public void run(String... args) throws Exception {

		loadActivitiesData();
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
					.content("Este taller está pensado para ayudar a los alumnos con el material que traen de la escuela, también tenemos una docente que les da  clases de lengua y matemática con una planificación propia para nivelar que vayan con más herramientas a la escuela" )
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

			}
		
			
			Activities activity4 = Activities.builder()
					.name("Tutorias")
					.content("Es un programa destinado a jóvenes a partir del tercer año de secundaria, cuyo objetivo es garantizar su permanencia en la escuela y construir un proyecto de vida que da sentido al colegio.")
					.image("image")
					.isActive(true)
					.build();
				     activitiesRepository.save(activity4);

			}

}