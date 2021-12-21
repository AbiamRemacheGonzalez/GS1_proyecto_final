-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-12-2021 a las 04:42:38
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gs1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `balances`
--

CREATE TABLE `balances` (
  `balanceId` int(255) NOT NULL,
  `memberId` int(255) NOT NULL,
  `groupId` int(255) NOT NULL,
  `balanceAmount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `balances`
--

INSERT INTO `balances` (`balanceId`, `memberId`, `groupId`, `balanceAmount`) VALUES
(1, 9, 36, 0),
(2, 10, 36, 0),
(3, 11, 36, 25),
(4, 12, 36, 0),
(6, 9, 37, 0),
(7, 9, 38, 0),
(8, 12, 38, 60);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bizumaccounts`
--

CREATE TABLE `bizumaccounts` (
  `accountId` int(255) NOT NULL,
  `userId` int(255) NOT NULL,
  `telephoneNumber` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `bizumaccounts`
--

INSERT INTO `bizumaccounts` (`accountId`, `userId`, `telephoneNumber`) VALUES
(4, 12, 689898989);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chunkpayments`
--

CREATE TABLE `chunkpayments` (
  `chunkId` int(255) NOT NULL,
  `balanceId` int(255) NOT NULL,
  `paymentId` int(255) NOT NULL,
  `chunkAmount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `chunkpayments`
--

INSERT INTO `chunkpayments` (`chunkId`, `balanceId`, `paymentId`, `chunkAmount`) VALUES
(18, 8, 19, 60),
(41, 3, 36, 25);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `creditcards`
--

CREATE TABLE `creditcards` (
  `owner` varchar(255) NOT NULL,
  `creditNumber` bigint(16) NOT NULL,
  `expiryDate` date NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `creditCardId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `creditcards`
--

INSERT INTO `creditcards` (`owner`, `creditNumber`, `expiryDate`, `userId`, `creditCardId`) VALUES
('Alejandro Lezcano', 5478451236978965, '2027-12-01', 10, 7),
('Isidro Bermudez', 5478451236547855, '2027-12-01', 11, 8),
('Hugo Bañola', 4321432143214321, '2025-11-01', 12, 9),
('H H H', 4222222222222222, '2025-01-01', 15, 12),
('A M M', 4089549584095845, '2027-05-01', 16, 13),
('P P P', 4980980980980980, '2027-07-01', 17, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `friends`
--

CREATE TABLE `friends` (
  `friendId` int(255) NOT NULL,
  `sourceUserId` int(255) NOT NULL,
  `destinationUserId` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `friends`
--

INSERT INTO `friends` (`friendId`, `sourceUserId`, `destinationUserId`) VALUES
(1, 9, 10),
(2, 10, 11),
(3, 11, 9),
(4, 9, 12),
(5, 9, 11),
(6, 9, 15),
(7, 9, 16),
(8, 10, 15),
(9, 17, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `groups`
--

CREATE TABLE `groups` (
  `userId` int(255) NOT NULL,
  `groupId` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `groups`
--

INSERT INTO `groups` (`userId`, `groupId`, `name`, `description`) VALUES
(9, 36, 'Alquiler Casa Rural Telde', 'Este es un grupo para los\nintegrantes de la casa.'),
(9, 37, 'Viaje New York', 'buen viaje			'),
(9, 38, 'Clase GS1', 'Este grupo sirve para dividir gastos.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `members`
--

CREATE TABLE `members` (
  `memberId` int(255) NOT NULL,
  `groupId` int(255) NOT NULL,
  `userId` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `members`
--

INSERT INTO `members` (`memberId`, `groupId`, `userId`) VALUES
(37, 36, 9),
(38, 36, 10),
(39, 36, 11),
(40, 36, 12),
(42, 37, 9),
(43, 38, 9),
(44, 38, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `payments`
--

CREATE TABLE `payments` (
  `paymentsID` int(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `destinationUserID` int(255) NOT NULL,
  `groupId` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `payments`
--

INSERT INTO `payments` (`paymentsID`, `title`, `amount`, `destinationUserID`, `groupId`) VALUES
(19, 'Agua', 120, 9, 38),
(36, 'Agua', 200, 9, 36);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paypalaccounts`
--

CREATE TABLE `paypalaccounts` (
  `paymentId` int(255) NOT NULL,
  `userId` int(255) NOT NULL,
  `paypalemail` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `paypalaccounts`
--

INSERT INTO `paypalaccounts` (`paymentId`, `userId`, `paypalemail`) VALUES
(5, 9, 'abiamrg.pay14@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `requests`
--

CREATE TABLE `requests` (
  `requestId` int(255) NOT NULL,
  `sourceUserId` int(255) DEFAULT NULL,
  `destinationUserId` int(255) NOT NULL,
  `requestType` char(1) NOT NULL,
  `groupId` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `requests`
--

INSERT INTO `requests` (`requestId`, `sourceUserId`, `destinationUserId`, `requestType`, `groupId`) VALUES
(35, 17, 16, 'F', NULL),
(37, 17, 9, 'F', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userId` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`firstname`, `lastname`, `email`, `password`, `userId`) VALUES
('Abiam', 'Remache', 'abiamrg.14@gmail.com', '$2a$12$nO0sTuhKHzeslniQTSnYtuo3wdmjNP.PYeR9gDBmmFKG.rx4zsj3W', 9),
('Alejandro', 'Lezcano', 'Ale@gmail.com', '$2a$12$5aSCJeeXsa2WG4Jq48P6W.d0fJjHgKNCa15J4gtvAVlXfHj9RpPC6', 10),
('Isidro', 'Bermudez', 'Isi@gmail.com', '$2a$12$7DNMlA2Q0RxEo6x4TS2j5ujuZAdVeZ4RJnnaWqVDY2dTuW3I4rfnO', 11),
('Hugo', 'Bañola', 'hugo@mail.com', '$2a$12$gd3LtH/1ePP8k9dTPvkacODjVrXCHR45d3nG9Gv6.vPmzmwDbYGaq', 12),
('Hector', 'Hernandez', 'hector@gmail.com', '$2a$12$i/7RtFN1cRJ9HTGmvSfMMul5Jcb4IujgpRNwB61oMxWKorpmyEqv2', 15),
('Alexander', 'Moreno', 'alex@gmail.com', '$2a$12$fsbzQ9OJsK5WwUaqVO7bN.5uF.xCqpFESo9NdQeAEzM7EYixfWpm2', 16),
('Piotr', 'Hernandez', 'piotr@gmail.com', '$2a$12$BJcXHL10Rvta/ZKi8tvx6Oijy38WDRdyqsQon9G61vaf0YSvby9PW', 17);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `balances`
--
ALTER TABLE `balances`
  ADD PRIMARY KEY (`balanceId`),
  ADD KEY `memberId` (`memberId`),
  ADD KEY `groupId` (`groupId`);

--
-- Indices de la tabla `bizumaccounts`
--
ALTER TABLE `bizumaccounts`
  ADD PRIMARY KEY (`accountId`),
  ADD KEY `userId` (`userId`);

--
-- Indices de la tabla `chunkpayments`
--
ALTER TABLE `chunkpayments`
  ADD PRIMARY KEY (`chunkId`),
  ADD KEY `balanceId` (`balanceId`),
  ADD KEY `paymentId` (`paymentId`);

--
-- Indices de la tabla `creditcards`
--
ALTER TABLE `creditcards`
  ADD PRIMARY KEY (`creditCardId`),
  ADD KEY `userId` (`userId`);

--
-- Indices de la tabla `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`friendId`),
  ADD KEY `sourceUserId` (`sourceUserId`),
  ADD KEY `destinationUserId` (`destinationUserId`);

--
-- Indices de la tabla `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`groupId`),
  ADD KEY `userId` (`userId`);

--
-- Indices de la tabla `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`memberId`),
  ADD KEY `userId` (`userId`),
  ADD KEY `groupId` (`groupId`);

--
-- Indices de la tabla `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`paymentsID`),
  ADD KEY `destinationUserID` (`destinationUserID`),
  ADD KEY `groupId` (`groupId`);

--
-- Indices de la tabla `paypalaccounts`
--
ALTER TABLE `paypalaccounts`
  ADD PRIMARY KEY (`paymentId`),
  ADD KEY `userId` (`userId`);

--
-- Indices de la tabla `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`requestId`),
  ADD KEY `sourceUserId` (`sourceUserId`),
  ADD KEY `destinationUserId` (`destinationUserId`),
  ADD KEY `groupId` (`groupId`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `balances`
--
ALTER TABLE `balances`
  MODIFY `balanceId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `bizumaccounts`
--
ALTER TABLE `bizumaccounts`
  MODIFY `accountId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `chunkpayments`
--
ALTER TABLE `chunkpayments`
  MODIFY `chunkId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `creditcards`
--
ALTER TABLE `creditcards`
  MODIFY `creditCardId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `friends`
--
ALTER TABLE `friends`
  MODIFY `friendId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `groups`
--
ALTER TABLE `groups`
  MODIFY `groupId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `members`
--
ALTER TABLE `members`
  MODIFY `memberId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `payments`
--
ALTER TABLE `payments`
  MODIFY `paymentsID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `paypalaccounts`
--
ALTER TABLE `paypalaccounts`
  MODIFY `paymentId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `requests`
--
ALTER TABLE `requests`
  MODIFY `requestId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `balances`
--
ALTER TABLE `balances`
  ADD CONSTRAINT `balances_ibfk_1` FOREIGN KEY (`memberId`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `balances_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `groups` (`groupId`);

--
-- Filtros para la tabla `bizumaccounts`
--
ALTER TABLE `bizumaccounts`
  ADD CONSTRAINT `bizumaccounts_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Filtros para la tabla `chunkpayments`
--
ALTER TABLE `chunkpayments`
  ADD CONSTRAINT `chunkpayments_ibfk_1` FOREIGN KEY (`balanceId`) REFERENCES `balances` (`balanceId`),
  ADD CONSTRAINT `chunkpayments_ibfk_2` FOREIGN KEY (`paymentId`) REFERENCES `payments` (`paymentsID`);

--
-- Filtros para la tabla `creditcards`
--
ALTER TABLE `creditcards`
  ADD CONSTRAINT `creditcards_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Filtros para la tabla `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`sourceUserId`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`destinationUserId`) REFERENCES `users` (`userId`);

--
-- Filtros para la tabla `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Filtros para la tabla `members`
--
ALTER TABLE `members`
  ADD CONSTRAINT `members_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `members_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `groups` (`groupId`);

--
-- Filtros para la tabla `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`destinationUserID`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `groups` (`groupId`);

--
-- Filtros para la tabla `paypalaccounts`
--
ALTER TABLE `paypalaccounts`
  ADD CONSTRAINT `paypalaccounts_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Filtros para la tabla `requests`
--
ALTER TABLE `requests`
  ADD CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`sourceUserId`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `requests_ibfk_2` FOREIGN KEY (`destinationUserId`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `requests_ibfk_3` FOREIGN KEY (`groupId`) REFERENCES `groups` (`groupId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
