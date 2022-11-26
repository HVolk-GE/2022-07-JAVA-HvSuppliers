-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 10. Jun 2022 um 11:26
-- Server-Version: 10.4.24-MariaDB
-- PHP-Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `suppliers`
--
CREATE DATABASE IF NOT EXISTS `suppliers` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `suppliers`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `deliveries`
--

CREATE TABLE `deliveries` (
  `_id` int(11) NOT NULL,
  `_idsup` int(11) NOT NULL,
  `deliverysupplier` text NOT NULL,
  `delieverymode` text NOT NULL,
  `deliverycontent` text NOT NULL,
  `deliveryfrom` text NOT NULL,
  `deliveryto` text NOT NULL,
  `deliverydays` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `deliveries`
--

INSERT INTO `deliveries` (`_id`, `_idsup`, `deliverysupplier`, `delieverymode`, `deliverycontent`, `deliveryfrom`, `deliveryto`, `deliverydays`) VALUES
(1, 1, 'Spedition Schmitz', 'LKW', 'Lebenmittel, Gemüse und Früchte', 'Hamburg', 'Frankfurt', 2),
(2, 1, 'Spedition Schmitz', 'LKW', 'Getränke', 'Hamburg', 'Hanover', 1),
(3, 4, 'Spedition Malican', 'Schiff', 'Kaffee', 'USA', 'Niederland', 15),
(4, 4, 'Spedition Malican', 'Schiff', 'Weizen', 'Deutschland', 'Katar', 14),
(5, 2, 'Spedition Mayer Schorch', 'LKW', 'Mais, Weizen', 'München', 'Nürnberg', 2),
(6, 3, 'Spedition Schäfer 	', 'LKW', 'Kalk', 'Rotterdam (NL)', 'Diez', 4),
(7, 4, 'Spedition Malican', 'Schiff', 'Kohle', 'Deutschland', 'Malta', 14),
(10, 2, 'Spedition Mayer Schorch', 'LKW', 'Bohnen', 'Hamburg', 'Bonn', 2),
(11, 1, 'Spedition Schmitz', 'Schiff', 'Container, Fahrzeuge', 'New York', 'Bremerhafen', 27),
(12, 1, 'Spedition Schmitz', 'Schiff', 'Fahrzeuge', 'New York', 'Bremerhafen', 27),
(13, 1, 'Spedition Schmitz', 'Schiff', 'Fahrzeug', 'New York', 'Bremerhafen', 0),
(14, 2, 'Spedition Mayer Schorch', 'LKW', 'Rinderfleisch', 'Nürnberg', 'München', 2),
(16, 3, 'Spedition Schäfer', 'LKW', 'Kalk-Silo', 'Diez', 'Rotterdam', 4),
(17, 3, 'Spedition Schäfer', 'LKW', 'Getränke', 'Bremen', 'Frankfurt', 3),
(18, 4, 'Spedition Malican', 'Transporter', 'Reifen - PKW', 'Stuttgart', 'Nürburg', 1),
(19, 4, 'Spedition Malican', 'LKW', 'Mais', 'Köln', 'Bonn', 0),
(20, 2, 'Spedition Mayer Schorch', 'Transporter', 'Sicherungen (500/23A)', 'Frankfurt Flughafen', 'Berlin (BER)', 1),
(21, 3, 'Spedition Schäfer', 'LKW', 'Container Metall-Schrott', 'Mittenwald an der Oder', 'Kaufbeuren', 0),
(22, 2, 'Spedition Mayer Schorch', 'PKW', 'Medizin', 'Hamburg', 'Flensburg', 1),
(24, 2, 'Spedition Mayer Schorch', 'LKW', 'Container, Fahrzeuge', 'Berlin', 'Marburg', 3),
(25, 2, 'Spedition Mayer Schorch', 'LKW', 'Äpfel', 'Schiedam', 'Aachen', 0),
(26, 3, 'Spedition Schäfer', 'LKW', 'Kalk', 'Diez', 'Mainz', 0),
(27, 2, 'Spedition Mayer Schorch', 'LKW', 'Test', 'Berlin', 'Bremen', 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `suppliers`
--

CREATE TABLE `suppliers` (
  `_id` int(11) NOT NULL,
  `SupplierName` text NOT NULL,
  `SupplierStreet` text NOT NULL,
  `SupplierCity` text NOT NULL,
  `supplierPostalCode` text NOT NULL,
  `PrefertDeliveryMode` text NOT NULL,
  `DeliveryMaterial` text NOT NULL,
  `averageaDeliveryDays` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `suppliers`
--

INSERT INTO `suppliers` (`_id`, `SupplierName`, `SupplierStreet`, `SupplierCity`, `supplierPostalCode`, `PrefertDeliveryMode`, `DeliveryMaterial`, `averageaDeliveryDays`) VALUES
(1, 'Spedition Schmitz', 'Industrie Straße 26', 'Mainhausen', '85699', 'LKW', 'Frachgut,Container,Flüssigkeiten', '14'),
(2, 'Spedition Mayer Schorch', 'Ostring 25', 'Berlin', '08569', 'Zug,LKW', 'Frachgut,Container', '1'),
(3, 'Spedition Schäfer', 'Offheimer Weg 3', 'Limburg', '65549', 'LKW', 'Frachtgut', '3'),
(4, 'Spedition Malican', 'Van Hooverstreet 4', 'Bremen', '32568', 'LKW, Schiff', 'Container', '11');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `deliveries`
--
ALTER TABLE `deliveries`
  ADD PRIMARY KEY (`_id`),
  ADD KEY `n-1_DeliveriesInSuppliers` (`_idsup`);

--
-- Indizes für die Tabelle `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `deliveries`
--
ALTER TABLE `deliveries`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT für Tabelle `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `deliveries`
--
ALTER TABLE `deliveries`
  ADD CONSTRAINT `n-1_DeliveriesInSuppliers` FOREIGN KEY (`_idsup`) REFERENCES `suppliers` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
