# BeautyAtHome

Plataforma para conectar profesionales de la belleza con clientes a domicilio. Permite descubrir por cobertura y tipo de servicio, agendar con disponibilidad real, ver portafolios (con consentimiento) y calificar mediante reseñas verificadas. Este repositorio contiene los artefactos del curso (UML y Documentación) con una arquitectura pensada para ser mantenible y escalable.

> Estado: Primera versión académica; el contenido evolucionará por entregas (R1 → R2 → R3).

---

## 🔎 ¿Qué hace diferente a BeautyAtHome?
- Agenda confiable: evita solapes y valida cobertura del cliente antes de confirmar.
- Confianza y privacidad: reseñas solo de reservas completadas; uso de fotos con consentimiento.
- Patrocinios de marca: profesionales que usan kits/productos de marcas específicas.

---

## 📁 Estructura del repositorio

```
ProyectoModelos/
├─ UML/                  # Diagrama(s) UML
│  └─ UML_BeautyAtHome.pdf   # UML principal
└─ Doc/                  # Documentación y backlog
   ├─ Ingeniería_de_Requerimientos.pdf        # Reporte de Ingeniería de Requerimientos (PDF)
   ├─ storymap.csv       # Mapa de historias de usuario (CSV)
```

---

## 🧠 Arquitectura (resumen)
Patrones aplicados en el diseño:
- Creacionales: Abstract Factory (kits por marca), Builder (Booking), Singleton (centro de notificaciones/agenda).
- Estructurales: Facade (orquestación de reserva), Proxy (consentimiento de imágenes), Composite (servicios y paquetes), Decorator (addons como premium/discount/travel).

Beneficios: bajo acoplamiento, extensibilidad (nuevas marcas/servicios sin romper código), privacidad por diseño y casos de uso simples desde una fachada.

---

## 🧩 UML
- Ubicación: `UML/UML_BeautyAtHome.pdf` (formato PDF). 
---

## 📚 Documentación
- Reporte (PDF): `Doc/Ingeniería_de_Requerimientos.pdf` con problema, benchmarking, requerimientos y evidencias.
- Historias de usuario: `Doc/storymap.csv` (estructura libre; una historia por fila). 

---

## 🛣️ Roadmap (académico)
- R1 (MVP): onboarding, perfil básico, búsqueda por cobertura/servicio, agenda y reservas, reseñas verificadas, privacidad base.
- R2 (Ampliaciones): patrocinio y filtros, duración de servicios, portafolio con consentimiento, reprogramación/cancelación, notificaciones, categorías.
- R3 (Compliance/operación): moderación de reseñas/imágenes, retención/exportación de datos, reportes.


## 👥 Equipo
- Santiago Andrés Benavides Coral — 20232020036
- Julian Dario Romero Buitrago — 20232020240
- Andres Felipe Garcia Vargas — 20231020176
- Alicia Pineda Quiroga — 20222020047
- Laura Nathaly Paez Cifuentes — 20232020055

Docente: Sebastian Camilo Vanegas Ayala

Hecho con ❤️ para el curso de Modelos de Programación.