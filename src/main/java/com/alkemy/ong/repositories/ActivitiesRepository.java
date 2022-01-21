package com.alkemy.ong.repositories;

import com.alkemy.ong.entities.ActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitiesRepository extends JpaRepository<ActivitiesEntity, Long> {

}
/*
    Crear modelo y repositorio base de Activities

    COMO desarrollador
    QUIERO agregar la entidad Activity
    PARA representar en la implementación la estructura de datos

    Criterios de aceptación:
    Nombre de tabla: activities.

    Campos:
    name: VARCHAR NOT NULL
    content: TEXT NOT NULL
    image: VARCHAR NOT NULL
    timestamps y softDelete
*/