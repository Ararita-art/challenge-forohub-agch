# ForoHub API

## 📋 Descripción  
ForoHub API es una aplicación backend diseñada para gestionar un sistema de foros donde los usuarios pueden:  
- Crear tópicos  
- Responder tópicos 

La API proporciona:  
🔒 Autenticación segura  
👥 Gestión de usuarios  
🗂️ Sistema de tópicos y respuestas  
📚 Categorización por cursos  
⚖️ Sistema de roles y permisos  

## 🚀 Funcionalidades  

### 🔐 Autenticación y Autorización  
- Sistema de login con JWT  
- Roles de usuario (ROLE_USUARIO, ROLE_MODERADOR, etc.)  

### 👨‍💻 Gestión de Usuarios  
- Registro de nuevos usuarios  
- Autenticación con perfiles  
- Validación de credenciales  

### 💬 Tópicos  
- CRUD completo de tópicos  
- Paginación y ordenamiento  
- Eliminación lógica (soft delete)  

### 💡 Respuestas  
- Sistema de respuestas anidadas  
- Marcado de soluciones aceptadas  
- Relación con tópicos y usuarios  

### 📂 Cursos  
- Categorización de tópicos  
- Búsqueda por categoría  
- Estadísticas de participación  

### 🛡️ Otras Características  
- Validación robusta de datos de entrada  
- Paginación en todos los listados  

## 💻 Tecnologías Utilizadas  

| Categoría       | Tecnologías                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| Lenguaje        | ![Java](https://img.shields.io/badge/Java-17-blue)                          |
| Framework       | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-green)          |
| Seguridad       | ![Spring Security](https://img.shields.io/badge/Spring_Security-6.0-red)    |
| Persistencia    | ![Hibernate](https://img.shields.io/badge/Hibernate-6.0-blueviolet)         |
| Base de Datos   | ![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)                     |
| Documentación   | ![SpringDoc](https://img.shields.io/badge/SpringDoc-OpenAPI_3.0-success)    |
| Utilidades      | ![Lombok](https://img.shields.io/badge/Lombok-1.18-pink)                    |

## 🔒 Seguridad  

### Principales Medidas  
- **Autenticación JWT** con tokens de 8 horas  
- **BCrypt** para encriptación de contraseñas  
- **Protección CSRF** deshabilitada (API stateless)  
- **Endpoints protegidos** por roles  
- **Validación exhaustiva** de datos de entrada  
- **Manejo centralizado** de excepciones

## Desarrolladora de la aplicación
Araceli Gámez

### Contacto
Si como yo, eres un entusiasta aprendiz de programación, contáctate conmigo a través de:
**GITHUB**: [https://github.com/Ararita-art](https://github.com/Ararita-art)
**LINKEDIN**: [www.linkedin.com/in/araceli-gámez-chávez-21a135312](www.linkedin.com/in/araceli-gámez-chávez-21a135312)
