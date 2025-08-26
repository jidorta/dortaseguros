Gestión de Pólizas – Proyecto Backend Java
Descripción

Este proyecto es una aplicación de gestión de pólizas de seguros desarrollada en Java 11 con Spring Boot. Permite gestionar usuarios, pólizas y la lógica de negocio asociada (coberturas, siniestros y primas).

Actualmente, la seguridad está implementada en la gestión de usuarios y autenticación. Otros endpoints pueden ser ampliados para incluir seguridad adicional según sea necesario.

Tecnologías utilizadas

Java 11

Spring Boot 2.7+

Spring Security (para autenticación y autorización de usuarios)

JPA / Hibernate

H2 / PostgreSQL (dependiendo de configuración)

Maven para gestión de dependencias

JUnit para pruebas unitarias

Funcionalidades principales

Gestión de usuarios: registro, login y roles con Spring Security.

Gestión de pólizas: CRUD básico de pólizas, incluyendo datos de cliente, tipo de seguro y fecha de vigencia.

Seguridad:

Endpoints de usuarios y autenticación protegidos con Spring Security.

Posibilidad de ampliar la seguridad al resto de endpoints según necesidades del proyecto.

Extensibilidad:

Se pueden añadir nuevas entidades como siniestros, primas, coberturas adicionales, etc.

Fácil integración con frontend Angular u otras aplicaciones cliente.
