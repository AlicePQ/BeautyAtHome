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
backend/beautyathome
├─ pom.xml                 # Gestión de dependencias y plugins
├─ src/main/java/com/beautyathome
│  ├─ api/controller       # Endpoints REST (p.ej. BookingController)
│  ├─ application          # Servicios, facades y validaciones
│  ├─ domain               # Modelo de dominio y patrones
│  └─ infrastructure       # Persistencia, media y proxies
└─ src/main/resources
	└─ application.yml      # Configuración (puertos, datasources, etc.)

```

---

## 🏗️ Arquitectura y Patrones de Diseño

El sistema implementa *15 patrones de diseño* organizados en tres categorías:

### 🎨 Patrones Creacionales (5)

| Patrón | Aplicación | Beneficio |
|--------|-----------|-----------|
| *Abstract Factory* | Creación de familias de profesionales (HairStylist, MakeupArtist, Manicurist) | Permite agregar nuevos tipos de profesionales sin modificar código existente |
| *Factory Method* | Creación de servicios específicos (HaircutService, MakeupService) | Delega la instanciación a subclases especializadas |
| *Builder* | Construcción de objetos complejos (Booking, Review, Service) | Facilita la creación paso a paso con interfaz fluida |
| *Singleton* | Instancia única de Agenda | Garantiza una única fuente de verdad para disponibilidad |
| *Prototype* | (Planificado para R3) | Clonación de configuraciones de servicios |

### 🔧 Patrones Estructurales (5)

| Patrón | Aplicación | Beneficio |
|--------|-----------|-----------|
| *Composite* | Jerarquía de servicios (ServiceLeaf, ServiceComposite) | Permite tratar servicios individuales y paquetes uniformemente |
| *Decorator* | Adiciones dinámicas a servicios (PremiumAddon, DiscountAddon, TravelFeeAddon) | Añade funcionalidades sin modificar la clase base |
| *Proxy* | Control de acceso a recursos sensibles (ReviewGuard, ConsentProxy, CoverageProxy) | Protege información y valida permisos |
| *Facade* | Interfaz simplificada del sistema (BeautyAtHomeFacade) | Oculta complejidad y coordina subsistemas |
| *DAO* | Abstracción de acceso a datos | Separa lógica de negocio de persistencia |

### 🎭 Patrones Comportamentales (5)

| Patrón | Aplicación | Beneficio |
|--------|-----------|-----------|
| *Strategy* | Algoritmos de pricing intercambiables (StandardPricing, LoyalClientPricing) | Permite cambiar estrategias de precio en runtime |
| *Observer* | Sistema de notificaciones para Booking | Desacopla notificaciones de lógica de reservas |
| *State* | Estados de una reserva (Pending, Confirmed, InProgress, Completed, Cancelled) | Encapsula transiciones de estado |
| *Chain of Responsibility* | Validación de reservas (Coverage, Availability, Consent, Payment) | Procesa validaciones en secuencia flexible |
| *Visitor* | Operaciones sobre servicios (PricingVisitor, DescriptionVisitor) | Añade operaciones sin modificar estructura |
| *Iterator* | Recorrido de servicios compuestos | Acceso secuencial sin exponer implementación |

### 🎁 Beneficios Arquitectónicos

- ✅ *Bajo Acoplamiento*: Los componentes interactúan a través de interfaces
- 🔄 *Alta Cohesión*: Cada clase tiene una responsabilidad clara
- 🚀 *Extensibilidad*: Agregar nuevas marcas, servicios o profesionales sin romper código
- 🔒 *Privacidad por Diseño*: Protección de datos integrada desde la arquitectura
- 🧪 *Testeable*: Componentes independientes facilitan pruebas unitarias
- 📖 *Mantenible*: Código organizado según principios SOLID
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
