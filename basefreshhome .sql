-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 18-11-2024 a las 20:43:16
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `basefreshhome`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `nombreCliente` varchar(50) DEFAULT NULL,
  `IdCliente` int(11) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `direccionCliente` varchar(50) DEFAULT NULL,
  `CalificacionCliente` int(11) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `calificacion_cliente` int(11) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `direccion_cliente` varchar(255) DEFAULT NULL,
  `nombre_cliente` varchar(255) DEFAULT NULL,
  `id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `IdEmpleado` int(11) NOT NULL,
  `nombreEmpleado` varchar(50) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `anosExperiencia` int(11) DEFAULT NULL,
  `edad` int(11) NOT NULL,
  `CalificacionEmpleado` int(11) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `calificacion_empleado` int(11) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `años_experiencia` smallint(6) NOT NULL,
  `nombre_empleado` varchar(255) DEFAULT NULL,
  `id_empleado` int(11) NOT NULL,
  `anos_experiencia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habilidades`
--

CREATE TABLE `habilidades` (
  `habilidad` varchar(255) DEFAULT NULL,
  `IdEmpleado` int(11) DEFAULT NULL,
  `IdHabilidad` int(11) NOT NULL,
  `id_empleado` int(11) DEFAULT NULL,
  `id_habilidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `IdPago` int(11) NOT NULL,
  `metodo` varchar(255) DEFAULT NULL,
  `tarifa` int(11) DEFAULT NULL,
  `FechaPago` datetime DEFAULT NULL,
  `idSolicitud` int(11) DEFAULT NULL,
  `fecha_pago` datetime(6) DEFAULT NULL,
  `id_solicitud` int(11) DEFAULT NULL,
  `id_pago` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `idSolicitud` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fechaSolicitud` date DEFAULT NULL,
  `nombreCliente` varchar(50) DEFAULT NULL,
  `direccionCliente` varchar(50) DEFAULT NULL,
  `tarifa` int(11) DEFAULT NULL,
  `IdPago` int(11) DEFAULT NULL,
  `IdCliente` int(11) DEFAULT NULL,
  `IdEmpleado` int(11) DEFAULT NULL,
  `IdHabilidad` int(11) DEFAULT NULL,
  `CalificacionCliente` int(11) DEFAULT NULL,
  `ObservacioneCliente` varchar(200) DEFAULT NULL,
  `CalificacionEmpleado` int(11) DEFAULT NULL,
  `ObservacioneEmpleado` varchar(200) DEFAULT NULL,
  `calificacion_cliente` int(11) DEFAULT NULL,
  `calificacion_empleado` int(11) DEFAULT NULL,
  `observacione_cliente` varchar(255) DEFAULT NULL,
  `observacione_empleado` varchar(255) DEFAULT NULL,
  `direccion_cliente` varchar(255) DEFAULT NULL,
  `fecha_solicitud` date DEFAULT NULL,
  `nombre_cliente` varchar(255) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_empleado` int(11) DEFAULT NULL,
  `id_habilidad` int(11) DEFAULT NULL,
  `id_pago` int(11) DEFAULT NULL,
  `id_solicitud` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`IdCliente`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`IdEmpleado`);

--
-- Indices de la tabla `habilidades`
--
ALTER TABLE `habilidades`
  ADD PRIMARY KEY (`IdHabilidad`),
  ADD KEY `IdEmpleado` (`IdEmpleado`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`IdPago`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`idSolicitud`),
  ADD KEY `IdPago` (`IdPago`),
  ADD KEY `IdCliente` (`IdCliente`),
  ADD KEY `IdEmpleado` (`IdEmpleado`),
  ADD KEY `IdHabilidad` (`IdHabilidad`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `habilidades`
--
ALTER TABLE `habilidades`
  ADD CONSTRAINT `habilidades_ibfk_1` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`IdPago`) REFERENCES `pagos` (`IdPago`),
  ADD CONSTRAINT `solicitud_ibfk_2` FOREIGN KEY (`IdCliente`) REFERENCES `cliente` (`IdCliente`),
  ADD CONSTRAINT `solicitud_ibfk_3` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`),
  ADD CONSTRAINT `solicitud_ibfk_4` FOREIGN KEY (`IdHabilidad`) REFERENCES `habilidades` (`IdHabilidad`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
