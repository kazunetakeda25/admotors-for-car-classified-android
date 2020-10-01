-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: mysql1.panacea-soft.co
-- Generation Time: Dec 12, 2019 at 08:10 PM
-- Server version: 5.7.25-log
-- PHP Version: 7.1.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `admotors_admin_dev`
--

-- --------------------------------------------------------

--
-- Table structure for table `bs_accept_offer`
--

CREATE TABLE `bs_accept_offer` (
  `id` varchar(255) NOT NULL,
  `item_id` varchar(255) NOT NULL,
  `from_user_id` varchar(255) NOT NULL,
  `to_user_id` varchar(255) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_accept_offer`
--

INSERT INTO `bs_accept_offer` (`id`, `item_id`, `from_user_id`, `to_user_id`, `added_date`) VALUES
('acpt_114177af746f945efe27f2e928c6795c', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', 'usr0d5bad98cf777bcfe315cee6b2295141', '2019-06-14 05:29:05');

-- --------------------------------------------------------

--
-- Table structure for table `bs_apis`
--

CREATE TABLE `bs_apis` (
  `api_id` varchar(255) NOT NULL,
  `api_constant` varchar(255) NOT NULL,
  `api_name` text NOT NULL,
  `order_by_field` varchar(255) NOT NULL,
  `order_by_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_apis`
--

INSERT INTO `bs_apis` (`api_id`, `api_constant`, `api_name`, `order_by_field`, `order_by_type`) VALUES
('api001', 'GET_ALL_MANUFACTURERS', 'GET ALL MANUFACTURERS API', 'added_date', 'desc'),
('api002', 'GET_ALL_WALLPAPERS', 'Get All the Wallpapers API', 'added_date', 'desc'),
('api003', 'SEARCH_WALLPAPERS', 'Search Wallpapers API', 'added_date', 'desc');

-- --------------------------------------------------------

--
-- Table structure for table `bs_api_keys`
--

CREATE TABLE `bs_api_keys` (
  `id` varchar(255) NOT NULL,
  `key` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_api_keys`
--

INSERT INTO `bs_api_keys` (`id`, `key`) VALUES
('apikey1', 'teampsisthebest');

-- --------------------------------------------------------

--
-- Table structure for table `bs_app`
--

CREATE TABLE `bs_app` (
  `app_id` varchar(255) NOT NULL,
  `app_home` varchar(255) NOT NULL,
  `app_grid` varchar(255) NOT NULL,
  `app_detail` varchar(255) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_app`
--

INSERT INTO `bs_app` (`app_id`, `app_home`, `app_grid`, `app_detail`, `added_date`) VALUES
('app1', 'home_2', 'grid_2', 'detail_2', '2019-01-25 09:31:38');

-- --------------------------------------------------------

--
-- Table structure for table `bs_app_settings`
--

CREATE TABLE `bs_app_settings` (
  `id` varchar(255) NOT NULL,
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_app_settings`
--

INSERT INTO `bs_app_settings` (`id`, `lat`, `lng`) VALUES
('app1', 16.879910, 96.173248);

-- --------------------------------------------------------

--
-- Table structure for table `bs_categories_request`
--

CREATE TABLE `bs_categories_request` (
  `request_cat_id` varchar(255) NOT NULL,
  `request_cat_name` varchar(255) NOT NULL,
  `request_cat_ordering` varchar(255) NOT NULL,
  `request_message` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_categories_request`
--

INSERT INTO `bs_categories_request` (`request_cat_id`, `request_cat_name`, `request_cat_ordering`, `request_message`, `status`, `added_date`) VALUES
('catcd8d1000de45963e9ba67a02cb50b60f', 'testing 16', '', 'testing testing 11', 2, '2019-05-28 08:47:58');

-- --------------------------------------------------------

--
-- Table structure for table `bs_chat_history`
--

CREATE TABLE `bs_chat_history` (
  `id` varchar(255) NOT NULL,
  `item_id` varchar(255) NOT NULL,
  `buyer_user_id` varchar(255) NOT NULL,
  `seller_user_id` varchar(255) NOT NULL,
  `nego_price` float NOT NULL,
  `buyer_unread_count` int(11) NOT NULL,
  `seller_unread_count` int(11) NOT NULL,
  `is_accept` tinyint(1) NOT NULL DEFAULT '0',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_chat_history`
--

INSERT INTO `bs_chat_history` (`id`, `item_id`, `buyer_user_id`, `seller_user_id`, `nego_price`, `buyer_unread_count`, `seller_unread_count`, `is_accept`, `added_date`) VALUES
('chat_a85d162d6ea82cdd6b0dc097ebc25004', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'usr8dad6e9376480737a9a725e5dab8c402', 'c4ca4238a0b923820dcc509a6f75849b', 60000, 0, 1, 0, '2019-12-12 07:39:11');

-- --------------------------------------------------------

--
-- Table structure for table `bs_contact`
--

CREATE TABLE `bs_contact` (
  `contact_id` varchar(255) NOT NULL,
  `contact_name` varchar(255) NOT NULL,
  `contact_email` varchar(255) NOT NULL,
  `contact_phone` varchar(255) DEFAULT NULL,
  `contact_message` text NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_contact`
--

INSERT INTO `bs_contact` (`contact_id`, `contact_name`, `contact_email`, `contact_phone`, `contact_message`, `added_date`) VALUES
('con07bb233c0d8adda42499c2a1a6084a7b', 'Han', 'han@gmail.com', '42342424', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus consequat orci nec consectetur efficitur. Nam porta vitae nunc eget facilisis. Aliquam scelerisque tempus eros, eu volutpat erat porta porta. ', '2019-01-21 05:16:45'),
('con885841ce59b584d546b9fe5208e3250d', 'ps client', 'psclient@gmail.com', '11223344', 'Duis aute irure dolor in reprehenderit in voluptate velit esse\ncillum dolore eu fugiat nulla pariatur', '2018-01-03 07:27:57'),
('cona09d611fe14a3d5ef3fe9583f1be1d04', 'Paul Austin', 'paulaustin@gmail.com', '231445120', 'hello', '2019-07-22 10:42:22'),
('cond1834b1d7a067890a1eb34d12872f164', 'Soe Kyaw Thu Aung', 'soekyawthuaung1992@gmail.com', '0994949499', 'Test', '2019-11-08 07:03:44');

-- --------------------------------------------------------

--
-- Table structure for table `bs_delete_history`
--

CREATE TABLE `bs_delete_history` (
  `id` varchar(255) NOT NULL,
  `type_id` varchar(255) NOT NULL,
  `type_name` varchar(255) NOT NULL,
  `deleted_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_delete_history`
--

INSERT INTO `bs_delete_history` (`id`, `type_id`, `type_name`, `deleted_date`) VALUES
('del_his_0661584318b3ec0209f8209ccc884572', 'itm_761ed0124f5ed8023e5a6adffaf4b304', 'item', '2019-11-25 11:37:51'),
('del_his_0b515545707f4f6a243a6833f0d5acf0', 'itm_b29e2e03bb30f2b42d32062a03284492', 'item', '2019-07-22 09:59:08'),
('del_his_2001c58b6acf3136ccdb1b4923a62494', 'itm_e59eb05cf3e809d108a545f0e6a93074', 'item', '2019-11-25 11:37:31'),
('del_his_204a4edd3a354f612ac7e25465c25afa', 'itm_238c6ca66c857d438bb6117110fa726e', 'item', '2019-06-12 17:43:04'),
('del_his_2621981628c816054159257ccadb27a7', 'itm_c8e66bd3be012eb8dd4aadb2d8eff09c', 'item', '2019-07-24 04:54:17'),
('del_his_2d132831728696f84ebcfa690cd19d2b', 'itm_b5b94047c17945ce17be6153b5770bae', 'item', '2019-06-12 17:43:09'),
('del_his_34d2d03f8425f9a8240574a33d5027cd', 'itm_8b7ec2061d9934ceabbe072c0f10b373', 'item', '2019-08-28 11:51:20'),
('del_his_3d7f1091e1cadbcfe019a2ce3616fb2e', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'item', '2019-07-23 05:15:30'),
('del_his_462b83cfaa1653e15341c5860fd3b54b', 'cat5d0e69c6de4e9c907464b77269d7c05b', 'category', '2019-05-27 08:32:58'),
('del_his_4735c9f2826ff361a26c1ed2124b5736', 'cat220aa2d1c573100b7bf4faa62606126f', 'category', '2019-05-27 08:32:42'),
('del_his_52d7f3b970a5fa99fe98250d183254bc', 'itm_8160dfa617f3ce6fc0e5890073ae7b7a', 'item', '2019-06-12 17:43:25'),
('del_his_5db48027590336225530c804d6dd9e32', 'itm_4e3b413af4bee62e74b4fe8b20ed0e18', 'item', '2019-11-25 11:38:14'),
('del_his_614a58f7991180126cfddcfa8bcc416a', 'itm_54e91c9fc6003095cc6188bf877da329', 'item', '2019-05-30 08:08:25'),
('del_his_6447ab86badc74627bca186780df5811', 'itm_c4fde45407ff52108514b20191521b49', 'item', '2019-06-12 17:42:55'),
('del_his_65522e43f6cf32124b6c629804abe5e4', 'itm_1a7f3815a51c3c3888ede85bbacb9b88', 'item', '2019-07-02 06:25:09'),
('del_his_6d02eaba7397ab8a96b2a93e6ffd563f', 'itm_4cf669fbc74d97d443a37591c996b6c6', 'item', '2019-06-12 17:42:59'),
('del_his_6e8b3f9a98d3c66e0b6687b35ff84215', 'itm_369203a8427218dd53eadac0764721a4', 'item', '2019-06-12 17:43:14'),
('del_his_6ff016b9b7eb8b5745ce8e366066e511', 'itm_f2d6e5c3aad76f5a2a73803f5d84c154', 'item', '2019-11-26 05:44:38'),
('del_his_748e6054f950f6badde0917601e286b1', 'itm_a40370eb1fbed0e6d9b345ff29c247e1', 'item', '2019-11-25 11:37:46'),
('del_his_7cf9a50d4a29d84254d83be68709c7e5', 'itm_ac8663d37536f00b48651a0c6d9ad3f2', 'item', '2019-11-25 11:38:29'),
('del_his_83122f728dcec69f15270833a66b283e', 'itm_64e14853dcd989adb7d4015ff1ccc2f7', 'item', '2019-06-12 17:43:59'),
('del_his_850c4f8c26b48841afcde4540aee2a63', 'itm_459cbe7fa11f6e27257c59d9189a0695', 'item', '2019-11-25 11:38:46'),
('del_his_874bf88ac5e4198c1d7cfcae67b841a7', 'subcatcffb2d3aaae701234834f94d870aead7', 'subcategory', '2019-07-22 09:42:45'),
('del_his_9ad4c438798e2dcff71b2fc5b754b7ed', 'subcat805f76c3524b56698c1c8073db8e6749', 'subcategory', '2019-07-22 09:37:23'),
('del_his_a15b0729d275b084bc7a83fa93f09ab7', 'itm_28423af18512f1fb457d63d9df5eabe4', 'item', '2019-11-25 11:37:24'),
('del_his_a1a99e5d4cd5455bbe30b1664186457d', 'itm_b444ee14cbc3b4071b4fc6bc2661a12b', 'item', '2019-11-25 11:38:39'),
('del_his_a1fc164884ad1345523e6f587ce70a37', 'itm_5a3564bdefc0dd458b6ed43f0f2d3db8', 'item', '2019-11-25 11:38:08'),
('del_his_a5b21bd7202a81d2972450cf3c2b1a74', 'itm_0b4909303ca4e4d93ced3ee75393f145', 'item', '2019-06-12 17:43:20'),
('del_his_a84b3a197a8e61be3ac8fa522bdfa65d', 'model647a0bacbe01ba015cc14252721483b7', 'model', '2019-10-24 23:22:13'),
('del_his_a9012fbe9eee14e05f35e1e38c2b6610', 'itm_4cfffaa29ac00e5c5828726fe334a573', 'item', '2019-11-25 11:38:34'),
('del_his_b640ecb3f9c119bd1bbdd0769d75a0e7', 'itm_41f5819047e39c60b69fbe0fd4037193', 'item', '2019-11-25 11:37:42'),
('del_his_b82ed6c89b8b19bc28d54d0b86b3704b', 'itm_20b9d70e3ed50250fe9899245d3a819c', 'item', '2019-11-25 11:37:56'),
('del_his_c571dee7dbe24693bbfe2ee4ba5f0952', 'itm_df00498f0ac018c80372cef801513700', 'item', '2019-08-29 03:28:27'),
('del_his_c633b9ee730498e04d98c309251c351f', 'itm_4377e2824023f47fb150132f421e3df4', 'item', '2019-11-25 11:37:36'),
('del_his_cfea8109010a204e73a023f661f6df94', 'itm_87d1a94f0ca98f6d35e5288a12f326cf', 'item', '2019-05-29 22:37:09'),
('del_his_d1b6f8cecf49825c9366bda85d695b30', 'itm_4a78f0927f1b6457ce897beff8f930d7', 'item', '2019-06-12 17:43:46'),
('del_his_d800f41ade56e0cc04c3a4402036e0a9', 'itm_bd61a105c2acff45a05d0650a4dc406e', 'item', '2019-05-29 23:10:16'),
('del_his_e4193c439f3f11ecc0d2de889b51ef2c', 'itm_5ef651576520dcbdf7237ec52e1d00fc', 'item', '2019-06-12 17:43:30'),
('del_his_e9975ec2681f8edb6fd726ff398b3fc3', 'itm_4efe52b5be1fa599109a64c96bb367ea', 'item', '2019-06-12 17:43:54'),
('del_his_fb44551c9353d7b1de4f836d14471fb7', 'itm_951b451ab6e8dc09689844f9b68bf22d', 'item', '2019-11-25 11:38:02'),
('del_his_ff161c5c97a89f9854356c2c2bbdb4e3', 'itm_6a497acc03b9d21b83cc80eb6f5a7345', 'item', '2019-05-30 08:08:19');

-- --------------------------------------------------------

--
-- Table structure for table `bs_favourite`
--

CREATE TABLE `bs_favourite` (
  `id` varchar(255) NOT NULL,
  `item_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_favourite`
--

INSERT INTO `bs_favourite` (`id`, `item_id`, `user_id`, `added_date`) VALUES
('favd940c8af6d3bb881c5d9a7be6b0e575b', 'itm_b6ca601edf04436a7e2516a36b5f0e8f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 09:49:45');

-- --------------------------------------------------------

--
-- Table structure for table `bs_feeds`
--

CREATE TABLE `bs_feeds` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_feeds`
--

INSERT INTO `bs_feeds` (`id`, `name`, `description`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `status`) VALUES
('feed2b30c87a3712da060e7afe346c3942fa', 'Toyota Cars Promotion', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ornare elit a malesuada malesuada. Integer fringilla risus sed risus commodo placerat. Sed mi velit, pulvinar a tempor nec, volutpat id lectus. Phasellus faucibus interdum eros non lobortis. Donec iaculis tempus mattis. Nullam sed lorem eget est tincidunt rhoncus nec', '2019-11-26 02:22:41', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 02:46:10', 'c4ca4238a0b923820dcc509a6f75849b', 1),
('feed3a6e6e7eef6d107a325440f3edbca146', 'Nissan Cars Big Sale', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ornare elit a malesuada malesuada. Integer fringilla risus sed risus commodo placerat. Sed mi velit, pulvinar a tempor nec, volutpat id lectus. Phasellus faucibus interdum eros non lobortis. Donec iaculis tempus mattis. Nullam sed lorem eget est tincidunt rhoncus nec', '2019-11-26 02:21:09', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 02:45:45', 'c4ca4238a0b923820dcc509a6f75849b', 1),
('feed46f2db96d64e82a65efeb0cff732563c', 'BMW M3 Cars Promotion', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ornare elit a malesuada malesuada. Integer fringilla risus sed risus commodo placerat. Sed mi velit, pulvinar a tempor nec, volutpat id lectus. Phasellus faucibus interdum eros non lobortis. Donec iaculis tempus mattis. Nullam sed lorem eget est tincidunt rhoncus nec', '2019-11-26 02:43:27', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 08:13:27', '', 1),
('feed4e6ed4ee41d2823b51b7e3afdf8306ac', 'Honda Cars 20% Discount', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ornare elit a malesuada malesuada. Integer fringilla risus sed risus commodo placerat. Sed mi velit, pulvinar a tempor nec, volutpat id lectus. Phasellus faucibus interdum eros non lobortis. Donec iaculis tempus mattis. Nullam sed lorem eget est tincidunt rhoncus nec', '2019-11-26 02:24:40', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 02:43:54', 'c4ca4238a0b923820dcc509a6f75849b', 1),
('feed6962717195222fd909e34ff2d08c5a26', 'BMW 2018 Model Sale', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ornare elit a malesuada malesuada. Integer fringilla risus sed risus commodo placerat. Sed mi velit, pulvinar a tempor nec, volutpat id lectus. Phasellus faucibus interdum eros non lobortis. Donec iaculis tempus mattis. Nullam sed lorem eget est tincidunt rhoncus nec', '2019-11-26 02:23:06', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 02:46:02', 'c4ca4238a0b923820dcc509a6f75849b', 1),
('feed72dc3543d6c48fa4abe261dd83de53d9', 'General Motors Promotion', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ornare elit a malesuada malesuada. Integer fringilla risus sed risus commodo placerat. Sed mi velit, pulvinar a tempor nec, volutpat id lectus. Phasellus faucibus interdum eros non lobortis. Donec iaculis tempus mattis. Nullam sed lorem eget est tincidunt rhoncus nec', '2019-11-26 02:23:48', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 02:44:53', 'c4ca4238a0b923820dcc509a6f75849b', 1),
('feeda03a4ef30100f631b4bed73193ab2074', 'Sell My New BMW X3 Car', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ornare elit a malesuada malesuada. Integer fringilla risus sed risus commodo placerat. Sed mi velit, pulvinar a tempor nec, volutpat id lectus. Phasellus faucibus interdum eros non lobortis. Donec iaculis tempus mattis. Nullam sed lorem eget est tincidunt rhoncus nec', '2019-11-26 02:41:45', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 08:11:45', '', 1),
('feedfc6119d3a5cde7f85635ffabd0143d65', 'Buy Old Toyota Car', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ornare elit a malesuada malesuada. Integer fringilla risus sed risus commodo placerat. Sed mi velit, pulvinar a tempor nec, volutpat id lectus. Phasellus faucibus interdum eros non lobortis. Donec iaculis tempus mattis. Nullam sed lorem eget est tincidunt rhoncus nec', '2019-11-26 02:42:23', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 08:12:23', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `bs_follows`
--

CREATE TABLE `bs_follows` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `followed_user_id` varchar(255) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `bs_home_banners`
--

CREATE TABLE `bs_home_banners` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_home_banners`
--

INSERT INTO `bs_home_banners` (`id`, `name`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`) VALUES
('ban37f83cc220683f5fe95bfb6da03131c3', 'banner 5', 1, '2019-10-29 15:36:26', '', '2019-10-29 15:36:26', '', 0),
('ban9befae4e71e14e40a01694caeed51c15', 'banner 1', 1, '2019-10-29 15:33:19', '', '2019-10-29 15:33:19', '', 0),
('bana9ec1f43cadeeec6841c523290cd185e', 'banner 4', 1, '2019-10-29 15:34:23', '', '2019-10-29 15:34:23', '', 0),
('banb0b338e97d3243927fb3b1df35632749', 'banner 2', 1, '2019-10-29 15:33:40', '', '2019-10-29 15:33:40', '', 0),
('band1330f9da20ad21a806154a93f0d5fca', 'banner 3', 1, '2019-10-29 15:34:01', '', '2019-10-29 15:34:01', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bs_items`
--

CREATE TABLE `bs_items` (
  `id` varchar(255) NOT NULL,
  `item_type_id` varchar(255) NOT NULL,
  `item_price_type_id` varchar(255) NOT NULL,
  `item_currency_id` varchar(255) NOT NULL,
  `item_location_id` varchar(255) NOT NULL,
  `condition_of_item_id` varchar(255) NOT NULL,
  `color_id` varchar(255) NOT NULL,
  `fuel_type_id` varchar(255) NOT NULL,
  `build_type_id` varchar(255) NOT NULL,
  `model_id` varchar(255) NOT NULL,
  `manufacturer_id` varchar(255) NOT NULL,
  `seller_type_id` varchar(255) NOT NULL,
  `transmission_id` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `highlight_info` text NOT NULL,
  `price` float NOT NULL,
  `business_mode` tinyint(1) NOT NULL DEFAULT '0',
  `is_sold_out` tinyint(1) NOT NULL DEFAULT '0',
  `title` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL,
  `touch_count` int(11) NOT NULL,
  `favourite_count` int(11) NOT NULL,
  `plate_number` varchar(255) NOT NULL,
  `engine_power` varchar(255) NOT NULL,
  `steering_position` varchar(255) NOT NULL,
  `no_of_owner` varchar(255) NOT NULL,
  `trim_name` varchar(255) NOT NULL,
  `vehicle_id` varchar(255) NOT NULL,
  `price_unit` varchar(255) NOT NULL,
  `year` varchar(255) NOT NULL,
  `licence_status` tinyint(1) NOT NULL DEFAULT '0',
  `max_passengers` int(11) NOT NULL,
  `no_of_doors` int(11) NOT NULL,
  `mileage` varchar(255) NOT NULL,
  `license_expiration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items`
--

INSERT INTO `bs_items` (`id`, `item_type_id`, `item_price_type_id`, `item_currency_id`, `item_location_id`, `condition_of_item_id`, `color_id`, `fuel_type_id`, `build_type_id`, `model_id`, `manufacturer_id`, `seller_type_id`, `transmission_id`, `description`, `highlight_info`, `price`, `business_mode`, `is_sold_out`, `title`, `address`, `lat`, `lng`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`, `touch_count`, `favourite_count`, `plate_number`, `engine_power`, `steering_position`, `no_of_owner`, `trim_name`, `vehicle_id`, `price_unit`, `year`, `licence_status`, `max_passengers`, `no_of_doors`, `mileage`, `license_expiration_date`) VALUES
('itm_016531de690206f9e03cf036c334a467', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_locc91e7fd5ffc739b26951228b0a564569', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model52842c22cf5d90d1dcaf40f10207a674', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 40000, 1, 0, 'BMW 600 three', '', 0.000000, 0.000000, 1, '2019-11-26 05:00:52', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-29 09:48:09', 'c4ca4238a0b923820dcc509a6f75849b', 0, 1, 0, 'mdy/9p-125', '550 CC', 'left', '1', 'trim name', 'BM1234', 'Lakh', '2020', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_070b8760ea6d199fa935644177cdd88c', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model8d62068148a2b2526fedbda4eee826e8', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 80000, 1, 0, 'BMW M3 five', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:33:49', 'usre16cb198d292bb9fdde6f2689a00f258', '2019-11-26 05:33:49', '', 0, 0, 0, 'ygn/9p-125', '600 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_348514eae52cb1c602c82153ffa456fc', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loca7b66748e03d457e976ca63a50e1bde0', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model602f53b845adfab3b1712ce60a23f389', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW X3 one', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:37:05', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-29 03:35:56', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_3d2a1d910b1a5e1586c0e22cd568ce0a', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model52842c22cf5d90d1dcaf40f10207a674', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 40000, 1, 0, 'BMW 600 two', '', 0.000000, 0.000000, 1, '2019-11-26 05:00:22', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 05:00:22', '', 0, 0, 0, 'mdy/9p-125', '550 CC', 'left', '1', 'trim name', 'BM1234', 'Lakh', '2020', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_3fb1417b6a7b46345d6cdb588e992235', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model52842c22cf5d90d1dcaf40f10207a674', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 50000, 1, 0, 'BMW 600 one', 'No. 221(B), Baker Street, London', 0.000000, 0.000000, 1, '2019-11-26 04:49:58', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-25 23:21:41', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'mdy/9p-125', '550 CC', 'left', '1', 'trim name', 'BM1234', 'Lakh', '2020', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_422f1f7006c5a0e0c5c67e337de2c0ac', '1', '3', 'itm_curencye14f08343f2d93328ce5caf1a72a7494', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', '', '', '', 'model891354c970ddbec3d44623f0a5cd5f89', 'manu03e8303f6bbbfba22ca2c4e867d4f182', '', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'test', '', 65, 0, 0, 'test', '', -6.208763, 106.845596, 1, '2019-11-29 10:14:39', 'usrcb9099221620a3553a5f0d80b481c063', '2019-11-29 10:14:39', '', 0, 0, 0, 'test', 'tesr', '', '', '', '', '', '6899', 1, 0, 0, '', '0000-00-00 00:00:00'),
('itm_510c7390b14b1db1ab64823e00cfe2cc', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model52842c22cf5d90d1dcaf40f10207a674', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 40000, 1, 0, 'BMW 600 four', '', 0.000000, 0.000000, 1, '2019-11-26 05:01:24', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 05:01:24', '', 0, 0, 0, 'mdy/9p-125', '550 CC', 'left', '1', 'trim name', 'BM1234', 'Lakh', '2020', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_533a2c3363d5bf2f7eafc9b2374c1940', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model26cd1d7b29739880ae5cacb9989fb62d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 85000, 1, 0, 'BMW Z8 one', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:44:43', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 05:44:43', '', 0, 0, 0, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_602c61360a8f91f17bf9619c0e5a45a2', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model26cd1d7b29739880ae5cacb9989fb62d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 85000, 1, 0, 'BMW Z8 five', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:47:39', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 05:47:39', '', 0, 0, 1, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_668ad28236dab1d28bad214f5fc39fcd', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'modelcca748a5ea71fbd5d017f6d2bf2fa36d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW Z4 four', '', 0.000000, 0.000000, 1, '2019-11-26 05:16:50', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 03:23:30', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'ygn/9p-125', '500 CC', 'left', '1', 'trim name', 'BM5678', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_69d4c9324c836f8fa7e05ef141f4a989', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model26cd1d7b29739880ae5cacb9989fb62d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 85000, 1, 0, 'BMW Z8 four', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:46:46', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 05:46:46', '', 0, 0, 0, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_6a9d75d302b4cd077c367e4c6c51c9ad', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model8d62068148a2b2526fedbda4eee826e8', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 80000, 1, 0, 'BMW M3 one', '', 0.000000, 0.000000, 1, '2019-11-26 05:29:43', 'usre16cb198d292bb9fdde6f2689a00f258', '2019-11-26 05:29:43', '', 0, 0, 0, 'ygn/9p-125', '600 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_6cb233f6c94ea092d2202e0c5d6d6229', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loca7b66748e03d457e976ca63a50e1bde0', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model26cd1d7b29739880ae5cacb9989fb62d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 85000, 1, 0, 'BMW Z8 three', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:46:01', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-29 03:28:39', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_7eccc7ea844c691ea7aae00efef07040', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model52842c22cf5d90d1dcaf40f10207a674', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 40000, 1, 0, 'BMW 600 five', '', 0.000000, 0.000000, 1, '2019-11-26 05:01:52', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 05:01:52', '', 0, 0, 1, 'mdy/9p-125', '550 CC', 'left', '1', 'trim name', 'BM1234', 'Lakh', '2020', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_96e0467a73c02f861d0609003a528d90', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model602f53b845adfab3b1712ce60a23f389', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW X3 two', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:39:13', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 05:39:13', '', 0, 0, 0, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_9ee01397bc7b3a5eb3696917fb9edbe2', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model26cd1d7b29739880ae5cacb9989fb62d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 85000, 1, 0, 'BMW Z8 two', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:45:14', 'c4ca4238a0b923820dcc509a6f75849b', '2019-12-12 07:35:14', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', '', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_a8bdffe7c03878acea4f6bb7095e6ecd', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'modelcca748a5ea71fbd5d017f6d2bf2fa36d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW Z4 five', 'N0 11. Main Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:17:24', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 03:23:13', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'ygn/9p-125', '500 CC', 'left', '1', 'trim name', 'BM5678', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_b43fab1945f629fe579e1a06eb56e5dc', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model8d62068148a2b2526fedbda4eee826e8', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 80000, 1, 0, 'BMW M3 four', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:33:21', 'usre16cb198d292bb9fdde6f2689a00f258', '2019-11-26 05:33:21', '', 0, 0, 0, 'ygn/9p-125', '600 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_b6ca601edf04436a7e2516a36b5f0e8f', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model602f53b845adfab3b1712ce60a23f389', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW X3 three', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:39:50', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 05:39:50', '', 0, 0, 1, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_bf0ddb1eef5ed6561f3185a05f849d89', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'modelcca748a5ea71fbd5d017f6d2bf2fa36d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW Z4 one', '', 0.000000, 0.000000, 1, '2019-11-26 05:13:58', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 03:24:35', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'ygn/9p-125', '500 CC', 'left', '1', 'trim name', 'BM5678', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_cbb02d168f9c3071567678dde6b94ac9', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_locc91e7fd5ffc739b26951228b0a564569', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'modelcca748a5ea71fbd5d017f6d2bf2fa36d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW Z4 two', '', 0.000000, 0.000000, 1, '2019-11-26 05:15:12', 'c4ca4238a0b923820dcc509a6f75849b', '2019-12-12 07:36:42', 'c4ca4238a0b923820dcc509a6f75849b', 0, 15, 0, 'ygn/9p-125', '500 CC', 'left', '1', 'trim name', 'BM5678', '', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 08:00:00'),
('itm_cc8f2cbbfe679b77de58a638338b447c', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_locc91e7fd5ffc739b26951228b0a564569', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model602f53b845adfab3b1712ce60a23f389', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW X3 four', 'No. 70, Railway Road, Pacitan', 1.354200, 103.819839, 1, '2019-11-26 05:40:21', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-29 03:40:27', 'c4ca4238a0b923820dcc509a6f75849b', 0, 7, 0, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh1', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 08:00:00'),
('itm_cced99dff3ef98ebe1acba11f6606378', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loca7b66748e03d457e976ca63a50e1bde0', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model602f53b845adfab3b1712ce60a23f389', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW X3 five', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:41:13', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-29 03:37:18', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'ygn/9p-125', '650 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_e9a2e1f53e66c26581434625e495099a', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model8d62068148a2b2526fedbda4eee826e8', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 80000, 1, 0, 'BMW M3 three', 'No. 70, Railway Road, Pacitan', 0.000000, 0.000000, 1, '2019-11-26 05:32:49', 'usre16cb198d292bb9fdde6f2689a00f258', '2019-11-26 05:32:49', '', 0, 0, 0, 'ygn/9p-125', '600 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_f93a8f648594175a784c03603950f8cf', '4', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '2', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'model8d62068148a2b2526fedbda4eee826e8', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 80000, 1, 0, 'BMW M3 two', '', 0.000000, 0.000000, 1, '2019-11-26 05:30:36', 'usre16cb198d292bb9fdde6f2689a00f258', '2019-11-26 05:30:36', '', 0, 0, 0, 'ygn/9p-125', '600 CC', 'left', '1', 'trim name', 'BM7852', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00'),
('itm_fd55561554addf7abc99ee1d574e4af7', '1', '2', 'itm_curency0d6e66664693165a0469cd3d1ba233f4', 'itm_loc413981c0f76ad269307aaee5eff59517', '1', 'color4e1bf990783775e2bf2c07350e3d6212', 'fuel48efb6fc71aba13fac15ab47a2edb212', 'color361ec35ad330c2ac2af2faa8f075c0fc', 'modelcca748a5ea71fbd5d017f6d2bf2fa36d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'sel89d0355b273ab9054ef59aa00033f2bb', 'tran9f5a0cff68baefc9b1db1fea3f11abcb', 'desc', 'info', 60000, 1, 0, 'BMW Z4 three', '', 0.000000, 0.000000, 1, '2019-11-26 05:16:06', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 03:24:00', 'c4ca4238a0b923820dcc509a6f75849b', 0, 0, 0, 'ygn/9p-125', '500 CC', 'left', '1', 'trim name', 'BM5678', 'Lakh', '2022', 1, 0, 0, '67 - 89 KM', '2019-11-26 17:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_build_types`
--

CREATE TABLE `bs_items_build_types` (
  `id` varchar(255) NOT NULL,
  `car_type` varchar(255) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_build_types`
--

INSERT INTO `bs_items_build_types` (`id`, `car_type`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`) VALUES
('color361ec35ad330c2ac2af2faa8f075c0fc', 'Mini Truck', 1, '2019-10-25 00:29:29', '', '2019-10-25 00:29:29', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_colors`
--

CREATE TABLE `bs_items_colors` (
  `id` varchar(255) NOT NULL,
  `color_value` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_colors`
--

INSERT INTO `bs_items_colors` (`id`, `color_value`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`) VALUES
('color062156573f190605c9c505a1b9beb1d6', 'Yellow', 1, '2019-10-25 00:23:12', '', '2019-10-25 00:23:12', '', 0),
('color286c53ac1f3f30db18aec0223343f75a', 'Black', 1, '2019-10-25 00:23:33', '', '2019-10-25 00:23:33', '', 0),
('color4e1bf990783775e2bf2c07350e3d6212', 'Red', 1, '2019-10-25 00:27:32', '', '2019-10-25 00:27:32', '', 0),
('colorb0f96822c9192ab87579ecc3ca51e784', 'Blue', 1, '2019-10-25 00:23:22', '', '2019-10-25 00:23:22', '', 0),
('colorf3858431056fcc468eaf42ae4ccd7c38', 'White', 1, '2019-10-23 22:09:42', '', '2019-10-23 21:48:25', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_currency`
--

CREATE TABLE `bs_items_currency` (
  `id` varchar(255) NOT NULL,
  `currency_short_form` varchar(255) NOT NULL,
  `currency_symbol` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_currency`
--

INSERT INTO `bs_items_currency` (`id`, `currency_short_form`, `currency_symbol`, `status`, `added_date`) VALUES
('itm_curency0d6e66664693165a0469cd3d1ba233f4', 'USD', '$', 1, '2019-05-30 04:30:37'),
('itm_curencye14f08343f2d93328ce5caf1a72a7494', 'GBP', '£', 1, '2019-07-22 10:07:41');

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_fuel_types`
--

CREATE TABLE `bs_items_fuel_types` (
  `id` varchar(255) NOT NULL,
  `fuel_name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_fuel_types`
--

INSERT INTO `bs_items_fuel_types` (`id`, `fuel_name`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`) VALUES
('fuel33beabcf47518816ac6e03f1fcdec42f', 'Bio-diesel', 1, '2019-10-24 23:08:05', '', '2019-10-24 23:08:05', '', 0),
('fuel48efb6fc71aba13fac15ab47a2edb212', 'Petrol', 1, '2019-10-23 23:08:57', '', '2019-10-23 23:05:28', '', 0),
('fuel58df38faa5f63d3a1803e13c72a22a52', 'Diesel', 1, '2019-10-24 23:08:39', '', '2019-10-24 23:08:39', '', 0),
('fuel795006cde706fd87a1b4a6027bb7febd', 'Gasoline', 1, '2019-10-24 23:08:59', '', '2019-10-24 23:08:59', '', 0),
('fuelf7008e001fae98d074d2773c5f3cf3eb', 'Ethanol', 1, '2019-10-24 23:09:49', '', '2019-10-24 23:09:49', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_location`
--

CREATE TABLE `bs_items_location` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `lat` float(10,6) NOT NULL,
  `lng` float(10,6) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_location`
--

INSERT INTO `bs_items_location` (`id`, `name`, `lat`, `lng`, `status`, `added_date`) VALUES
('itm_loc413981c0f76ad269307aaee5eff59517', 'Pacitan', -6.208763, 106.845596, 1, '2019-09-12 16:29:34'),
('itm_loca7b66748e03d457e976ca63a50e1bde0', 'Mandalay', 21.959900, 96.086601, 1, '2019-06-06 23:39:05'),
('itm_locc91e7fd5ffc739b26951228b0a564569', 'Singapore', 1.354200, 103.819839, 1, '2019-06-06 23:34:36');

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_price`
--

CREATE TABLE `bs_items_price` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_price`
--

INSERT INTO `bs_items_price` (`id`, `name`, `status`, `added_date`) VALUES
('1', 'Fixed', 1, '2019-05-28 22:54:44'),
('2', 'Negotiable', 1, '2019-05-28 22:54:44'),
('3', 'Price on call', 1, '2019-05-28 22:55:17'),
('4', 'Auction', 1, '2019-05-28 22:55:17'),
('5', 'Free', 1, '2019-05-30 04:29:09'),
('6', 'No price', 1, '2019-05-28 22:55:47');

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_report`
--

CREATE TABLE `bs_items_report` (
  `id` varchar(255) NOT NULL,
  `item_id` varchar(255) NOT NULL,
  `reported_user_id` varchar(255) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_report`
--

INSERT INTO `bs_items_report` (`id`, `item_id`, `reported_user_id`, `added_date`) VALUES
('itm_report058e4089d2de99821d47f7730bcf3707', 'itm_7da4b726eeb3a492413789219c83c6b2', '', '2019-07-02 06:01:41'),
('itm_report0af67b1a2c6f46d81a178d226412a493', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:47:37'),
('itm_report0e5f6ed52246890967f011c61bf392a7', 'itm_e8a3f1402c52c8f21844305f2739fd7b', '', '2019-09-25 23:16:48'),
('itm_report18703f2901877cb2f018578b33432a12', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:06:00'),
('itm_report2edd0e867fe3b6b58c0b8bc588f61093', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:08:29'),
('itm_report506495b5ec5297e9c17ae59fb3a29be7', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 00:26:15'),
('itm_report6466ea3a5a9be28a5bf2d14daa0cc716', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 22:57:04'),
('itm_report665562b7206319ddce0d37e2674132d1', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:46:53'),
('itm_report6ead42118fc44a0b3b0625f7df956c11', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:06:02'),
('itm_report74e097f7936f049391a8cccb804cb8e7', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:08:26'),
('itm_report83f8f615759674321f75b81ddad5ac0f', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:13:48'),
('itm_report840d2c25a34441f41747195cac05d89b', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-30 13:45:34'),
('itm_report8a842e30d14b13808b79902283940267', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:45:08'),
('itm_report9a4af5bd27641886db4d29e638cc485b', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:08:23'),
('itm_report9b2b20e5a7bb858f7a32d08d9566fde3', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:48:12'),
('itm_reporta30b8cb11fc83df8c2bed26a69e44347', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:47:48'),
('itm_reporta3af5e2befd7077eb8ce3b20e31cda47', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-01 07:04:14'),
('itm_reporta8403146e77f7c56779bc3c9c74f84e0', 'itm_65b0d9006e805ab13d727f6b2e06af6d', '', '2019-07-02 09:51:46'),
('itm_reportb39cbfbb668b446a663d5e208c1916ad', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:06:19'),
('itm_reportbf0e574c20d38c6e00e8d70ba1a78c21', 'itm_9ef57934867903960a9849bb7af133e0', '', '2019-10-01 06:46:25'),
('itm_reportc7c20f66b45ba683c8fc6f32d02a9cad', 'itm_65b0d9006e805ab13d727f6b2e06af6d', '', '2019-07-02 09:51:43'),
('itm_reportcafdba55ee5acacf8e6a0859f26e23ac', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:38:40'),
('itm_reportd26f1b7b95d288f31caef2ae27d7ee90', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-05-31 04:19:44'),
('itm_reportd73cfd0833f27bc66cd7d0bdd2b5307e', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:48:20'),
('itm_reportd8db5b8e1a79f9ac45f9c2e255110561', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:06:17'),
('itm_reportda091fd5171c647856e256ef5c477dcb', 'itm_f1d3630ad14ea7139fa9c123f718cce1', '', '2019-09-28 00:19:16'),
('itm_reportdaa4090efe1b0016442d98e007255267', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:05:59'),
('itm_reportdfcf3b1e018985cec70fcbd0bd4e2c54', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 22:50:16'),
('itm_reporte3e4884287c9cb880b88fc5c66f5a792', 'itm_7da4b726eeb3a492413789219c83c6b2', '', '2019-07-02 06:02:33'),
('itm_reportf79ddc06ab40cc22d5246af3ad42e621', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 06:28:07'),
('itm_reportf9cc3fd5f2547a16a9e3962423fb2029', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 07:11:01');

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_seller_types`
--

CREATE TABLE `bs_items_seller_types` (
  `id` varchar(255) NOT NULL,
  `seller_type` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_seller_types`
--

INSERT INTO `bs_items_seller_types` (`id`, `seller_type`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`) VALUES
('sel89d0355b273ab9054ef59aa00033f2bb', 'Showroom', 1, '2019-10-25 00:28:31', '', '2019-10-25 00:28:31', '', 0),
('selc5c5a698be74a69bccc06b120fce297d', 'Dealer', 1, '2019-10-25 00:28:04', '', '2019-10-25 00:28:04', '', 0),
('self2b74780b7d4978441b68262aeeae0f2', 'Private Seller', 1, '2019-10-25 00:28:18', '', '2019-10-25 00:28:18', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_transmissions`
--

CREATE TABLE `bs_items_transmissions` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_transmissions`
--

INSERT INTO `bs_items_transmissions` (`id`, `name`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`) VALUES
('tran207f55d12efbe46b402d5307cef3990d', 'Auto', 1, '2019-10-25 00:28:58', '', '2019-10-25 00:28:58', '', 0),
('tran9f5a0cff68baefc9b1db1fea3f11abcb', 'Manual', 1, '2019-10-25 00:29:10', '', '2019-10-25 00:29:10', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bs_items_types`
--

CREATE TABLE `bs_items_types` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_items_types`
--

INSERT INTO `bs_items_types` (`id`, `name`, `status`, `added_date`) VALUES
('1', 'Buy', 1, '2019-05-28 22:52:59'),
('2', 'Exchange', 1, '2019-05-28 22:52:59'),
('3', 'Lost and Found', 1, '2019-05-28 22:53:32'),
('4', 'Sell', 1, '2019-05-28 22:53:32');

-- --------------------------------------------------------

--
-- Table structure for table `bs_item_conditions`
--

CREATE TABLE `bs_item_conditions` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_item_conditions`
--

INSERT INTO `bs_item_conditions` (`id`, `name`, `status`, `added_date`) VALUES
('1', 'Use', 1, '2019-06-06 23:55:07'),
('2', 'New', 1, '2019-06-06 23:55:07');

-- --------------------------------------------------------

--
-- Table structure for table `bs_manufacturers`
--

CREATE TABLE `bs_manufacturers` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_manufacturers`
--

INSERT INTO `bs_manufacturers` (`id`, `name`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`) VALUES
('manu03e8303f6bbbfba22ca2c4e867d4f182', 'Nissan', 1, '2019-10-29 16:36:56', '', '2019-10-24 23:46:02', '', 0),
('manu27aad03ae2ca70c3e527e6838c6ae86c', 'Tesla', 1, '2019-11-29 08:49:02', '', '2019-11-29 08:49:02', '', 0),
('manu2be8a1bda6ba563cbf1c289ddc6bd978', 'General Motors', 1, '2019-10-24 23:51:21', '', '2019-10-24 23:51:21', '', 0),
('manu77e62e7ae4effdc79e5b211c60665c3d', 'Ford', 1, '2019-11-29 08:41:17', '', '2019-11-29 08:41:17', '', 0),
('manuaaa4b4db89d7150643621dcaf043ddd7', 'BMW', 1, '2019-10-29 16:37:27', '', '2019-10-24 23:41:47', '', 0),
('manudf9c1b40460fb53680fa5278f8ae8919', 'Toyota', 1, '2019-10-24 23:55:57', '', '2019-10-24 23:55:57', '', 0),
('manue3ec39506fb31e8a9a32ded499455851', 'KIA', 1, '2019-11-29 08:48:12', '', '2019-11-29 08:48:12', '', 0),
('manuea8bed8f89456f6cae100cce1e88bdf1', 'Honda', 1, '2019-10-24 23:48:06', '', '2019-10-24 23:48:06', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bs_models`
--

CREATE TABLE `bs_models` (
  `id` varchar(255) NOT NULL,
  `manufacturer_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_user_id` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` varchar(255) NOT NULL,
  `updated_flag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_models`
--

INSERT INTO `bs_models` (`id`, `manufacturer_id`, `name`, `status`, `added_date`, `added_user_id`, `updated_date`, `updated_user_id`, `updated_flag`) VALUES
('model26cd1d7b29739880ae5cacb9989fb62d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'BMW Z8', 1, '2019-11-25 23:05:18', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 04:35:18', '', 0),
('model38555e6b1564cf8d73a53a3fca4c1f7a', 'manuea8bed8f89456f6cae100cce1e88bdf1', 'CR-Z', 1, '2019-10-25 08:17:47', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-25 00:17:47', '', 0),
('model3dfac2764f4caf50477e9693141fbcc3', 'manudf9c1b40460fb53680fa5278f8ae8919', 'RAV4', 1, '2019-10-25 08:07:43', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-25 00:07:43', '', 0),
('model52842c22cf5d90d1dcaf40f10207a674', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'BMW 600', 1, '2019-11-26 04:31:31', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-25 23:01:31', 'c4ca4238a0b923820dcc509a6f75849b', 0),
('model602f53b845adfab3b1712ce60a23f389', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'BMW X3', 1, '2019-11-25 23:03:20', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 04:33:20', '', 0),
('model891354c970ddbec3d44623f0a5cd5f89', 'manu03e8303f6bbbfba22ca2c4e867d4f182', 'OMNI', 1, '2019-10-25 08:12:04', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-25 00:12:04', '', 0),
('model8d62068148a2b2526fedbda4eee826e8', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'BMW M3', 1, '2019-11-25 23:01:18', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 04:31:18', '', 0),
('model9f102e76d7d3b03b576f8bd57d665026', 'manudf9c1b40460fb53680fa5278f8ae8919', 'Tacoma', 1, '2019-10-25 08:05:01', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-25 00:05:01', '', 0),
('modelb69e8ea91dfe143b8c7cad5d9a0b730f', 'manu2be8a1bda6ba563cbf1c289ddc6bd978', 'Buick Verano', 1, '2019-10-25 00:24:41', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-25 08:24:41', 'c4ca4238a0b923820dcc509a6f75849b', 0),
('modelcca748a5ea71fbd5d017f6d2bf2fa36d', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'BMW Z4', 1, '2019-11-25 23:04:17', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-26 04:34:17', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bs_order_by`
--

CREATE TABLE `bs_order_by` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_order_by`
--

INSERT INTO `bs_order_by` (`id`, `name`) VALUES
('added_date_asc', 'Added Date Asc'),
('added_date_desc', 'Added Date Desc'),
('name_asc', 'Wallpaper Name Asc'),
('name_desc', 'Wallpaper Name Desc'),
('point_asc', 'Point Asc'),
('point_desc', 'Point Desc');

-- --------------------------------------------------------

--
-- Table structure for table `bs_push_notification_messages`
--

CREATE TABLE `bs_push_notification_messages` (
  `id` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `description` text NOT NULL,
  `added_user_id` varchar(255) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_push_notification_messages`
--

INSERT INTO `bs_push_notification_messages` (`id`, `message`, `description`, `added_user_id`, `added_date`) VALUES
('noti5133d4cca0f78d28528fe89da2041168', 'fsdfdsf', 'dsfdsf', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 05:29:39'),
('noti97225230a7badf761c5d2e4542b616cb', 'hfkjds', 'sdfsd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 05:35:46'),
('notia12d6755b0fe70765f8834a10cd51e6e', 'Test1', 'Test 1 description', 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-08 07:18:04'),
('notia4f5cc0da551a0d999c0ee13504b072b', 'dsdsd', 'sadasd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 05:34:27'),
('notic9a3ad3dca6672cc5075efbc9ca39ccc', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas et convallis diam, a eleifend augue. Nulla non purus egestas, rhoncus augue condimentum, finibus ante. Proin aliquam nulla in aliquam fermentum. Aenean eget consequat velit, vitae faucibus sapien. Nullam ipsum nisi, auctor a fringilla a, luctus vitae ligula. Suspendisse augue erat, tincidunt ut fringilla auctor, tincidunt in ipsum. Aliquam fringilla diam quis diam dapibus suscipit', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas et convallis diam, a eleifend augue. Nulla non purus egestas, rhoncus augue condimentum, finibus ante. Proin aliquam nulla in aliquam fermentum. Aenean eget consequat velit, vitae faucibus sapien. Nullam ipsum nisi, auctor a fringilla a, luctus vitae ligula. Suspendisse augue erat, tincidunt ut fringilla auctor, tincidunt in ipsum.', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 02:26:23'),
('noticb8657dc0b0158ca9168ae6bc98254c0', 'test noti', 'desc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 08:23:17'),
('notie3a73fc9d0d1cb34b1fc5fbb973d10cc', 'test', 'desc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:59:53');

-- --------------------------------------------------------

--
-- Table structure for table `bs_push_notification_tokens`
--

CREATE TABLE `bs_push_notification_tokens` (
  `push_noti_token_id` varchar(255) NOT NULL,
  `device_token` text,
  `platform_name` varchar(50) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_push_notification_tokens`
--

INSERT INTO `bs_push_notification_tokens` (`push_noti_token_id`, `device_token`, `platform_name`, `user_id`, `added_date`) VALUES
('noti_tkn_028755fff1df55c7f4cd657b7bc26937', 'dJbFQY_uIjI:APA91bGPgMPg4OG_RbEu7zitdrAGbPsj6J9iftQ1LvnxeNdR7SwvBh-vHeueATVoeMvlTQy3qx4H8GfdOxCRlPdaBoMAKr1tAGZXfhRLNg-XQw2mz5C6pFaUJ4vH70mx_WzRw7K63gn6', 'android', '', '2019-11-13 05:04:17'),
('noti_tkn_02d83615402cacf77fc28bfb6173cfbe', 'eV2eFjTHENg:APA91bFjTp5zuvY565D6Yi-btR6bdsPbjhWsknWYl5QM4GpOJ7fsCPk90NlYdCTZ0uKtA-UWM-0sbfIvmGJpSz3KuA3dYbhJfIsYp4tlpqSQ_ryZE4TCnkn2RGLSxhd7CPOkmudcVOmO', 'android', '', '2019-11-05 04:22:06'),
('noti_tkn_06ee9fb2a098c6f7bd98d6f63f0101eb', 'cLTClPKM6Gs:APA91bHHgr9HLpiZHAmMx0dvWQL1A8iafS9UIUew8HRguGhvHkgp6vLQvbfdRiFmFUCA05WlpZmQ-ETyz5Or1nXtiipLfEsjF8dgtZx3aosZIk3SwwHJWxy3ryYiYd_kYw78BP-jdSDk', 'android', '', '2019-11-01 09:55:25'),
('noti_tkn_0b923761b4e147a5decd668c3c01e593', 'cE9kSKWfzCM:APA91bHoYT9xeH-kIVF_tT9djLAqCBhlHmJE6wndkfMsbWVpZGL5WCpgeqG4unZdEtr4ELHmcpj1tZj69UDfwGZDjHLKHCAxuInfwgAGMCWiLt0GxF4bkOM_RSSqLtQAy85ujEj-mjwJ', 'android', '', '2019-11-13 05:20:11'),
('noti_tkn_0ba6dd1d9471229fe5593b4dae6685c2', 'dZjQIjciL8I:APA91bHWo_G905pnhwzUUZoysmCEeY5xi4qzeUyxiZTIYZ5CHLc1lN6UZl8BZK5H8P0BR17A96MYCJkMFl0GLhDCSBU-13zLo2nbhdujK53MpfWN3KiDnHdoDI0vF5dIga0xGSNcKFDb', NULL, 'usr6056a7f82be58aded6e9315b8101d4e1', '2019-10-08 09:57:57'),
('noti_tkn_0e98a8cd3b92575c67921b421038b187', 'eTn4dj8sbro:APA91bGduN-Mq5XwHcwM4mknzW-pzkxwZjxp166LwabZTGxAScLRXqaGAogGSjJzKFWJ5xeAlsmyWD0yvG8OZp4Ea4RepBfknhAaojf9ZZvEun_YzMPTbKMuEgtpXNGp7dlTPhfWbAEu', 'android', '', '2019-11-15 10:03:41'),
('noti_tkn_0ea3ea608ea0deb3c9d5eebd6374d632', 'dvesKky1Tug:APA91bHsij5DhNOK9LW7_kOCHy4ORBkstjBUY-aoES2wNrvuWhlbD4WPwJbPSCYve7NcKnqc9-xp0rslFJ5fJZjYUBrXwBaod1g7-cHjjNVfUCfya_TlWUITDaYQw-q4-myQ2lhLKfS2', 'android', '', '2019-10-18 05:13:52'),
('noti_tkn_0ec682c2e5feaecc4c027ab3b740a7a4', 'egl8ozHTYvg:APA91bE03ykKqUn04HGMBGxlUKpIPey9iYecpBReaIRKTOZApMmVMCQ9vXXXM3mt2tlFcP8rdP3ZxyWAevki5P6QcZm-VaxCeLcy_qkHoMlvpMuuu9-al6CrJz72oPC6RfAxMYtXDGN1', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-14 03:41:59'),
('noti_tkn_1372c5d2a84f498e5714fab0e166a548', 'cLTClPKM6Gs:APA91bHHgr9HLpiZHAmMx0dvWQL1A8iafS9UIUew8HRguGhvHkgp6vLQvbfdRiFmFUCA05WlpZmQ-ETyz5Or1nXtiipLfEsjF8dgtZx3aosZIk3SwwHJWxy3ryYiYd_kYw78BP-jdSDk', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-01 09:55:46'),
('noti_tkn_140276d9fbb8fbab2ce1c1ea76637f34', 'dIwU1Dm4TXo:APA91bHYyOH2dXeiBVzOaw3e9CHhT5mFtNHSID6ZfDb9Fe5D50q9WcqMyXQl4ZJxNxAPPDOUK1Gr1YaMeBBwjRevWq02IOVIz3m4GLUoAaCaE5W1By4gwuKPk_UPHlFzpZAMHJOUkWWz', 'android', '', '2019-11-01 06:59:50'),
('noti_tkn_1495e7e1b09fc3bad3e59a0b3e8d031f', 'fF3E9NrYdPo:APA91bGHRorfhH3_dQ4Gf0c1gzwbSF1_z37fti05rz0PeeELdz7ptlgAuVyEYn9tfudSWD5fdLVAxdJywUI3uOHhXG-wbZAtaNSlgHebwL70uVLXK1dBobwiWmygZYfOJ5qNaEcYtOnT', 'android', '', '2019-11-18 05:51:31'),
('noti_tkn_182debb3caddc1174d1a673f934da671', 'dvesKky1Tug:APA91bHsij5DhNOK9LW7_kOCHy4ORBkstjBUY-aoES2wNrvuWhlbD4WPwJbPSCYve7NcKnqc9-xp0rslFJ5fJZjYUBrXwBaod1g7-cHjjNVfUCfya_TlWUITDaYQw-q4-myQ2lhLKfS2', 'android', '', '2019-10-18 04:04:07'),
('noti_tkn_18b907ae7f7056cda446f15447cd2037', 'dSvI5S3Ucew:APA91bELp0tRm3MYvTKLxFDmME2kLuKywLg2qvgv37h9tckSAbma9ZZJFAGplH3Q-lGGkDTfmsifuI81kqylEkOAO15FgIp3NyHPEwVJ_Gw6mMJA0J6dJkIsR2Jq1HpQQ4DAzkqCP3P6', 'android', '', '2019-10-31 04:28:43'),
('noti_tkn_1909b16a249190bce2c16dc3ead6d5ad', 'fPYFFymitDQ:APA91bHWMiajrAtb-JeGYW8AFI8yjGBM5Y1yW4QP-8J3cJ0ccAWa-y2Z8-gLF8z47VDejulTZuj3DVq7CQnEqEdhBlA6SnpOHcFht-zgvpM_j9czuuE1CMNdrs7gjwtEoRAWgJlmHNjH', 'android', '', '2019-11-04 09:39:57'),
('noti_tkn_1a1e3155b6843bb8d080638849d98040', 'fKVPtwaUDsA:APA91bHlOfbgY1-s2PPCakLv28hZGzF9_RO6IKT0CmfWtvBJiVUS83nrDo2zKaMhikzr8IdjqgA2bPlJFGmavFEiII7wnyd-MxmPY9O4ueTSgcx8tDTc-DNRi8whnorB9D8nMhBUJRir', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-04 03:16:22'),
('noti_tkn_1a91f1253b6ff72fba85bb70c5d42892', 'cCj2NWNo3Xs:APA91bEUI6TWshcki87LebP90HKcJ680WOtxBLpuCw8YXxsQwKFCgOTvRMAqeU4VW01HEXjBjy8ZxXUNkw0zUPX-c8kDmHlcm0jkB0CWWpMnai3ApJHfn-L0-yJKjPqHztJnio2074Fo', 'android', '', '2019-11-19 06:47:57'),
('noti_tkn_1b3ecf4de785edba2f5e7c17481e64bf', 'epyevavrVFQ:APA91bElQbhQ0_YILVcHSidVsqgR8YvTUqUYLrNdH5Zi1vBi-7EG1au6_gE7wlUHfSiwPgkk682DeNCzdGKB43lR6cQfjIJwA5wpHYdiD_I6KxbgHcW1Div8SgQs4F9oVQfPCbNqpxKv', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-04 03:52:57'),
('noti_tkn_1bb2bad333cc1ccbfe6ae912976a7a40', 'cT7KJ-zqrp8:APA91bHLFyJZcR-8GioaU2MfA2sPvWH0-GF9516kgMjoUFyyMrenO70Vp3khrpzS1J2CfZukzaTDxqyzF8eKftFItnSObfKlrHtVhSXWtjtgeW9bHXo6E-KgFAhi85U92IjGYDP-Qqsg', 'android', '', '2019-11-15 10:57:18'),
('noti_tkn_1be095c853271a0beb6b4acd2a1078f4', 'c5KkZOGx3gc:APA91bHq-3ZM-rsoXbNRi3K63aO1neC15qxQ6rSiL5simrerzXYo1tdaZUO301Qy743QG0IgNniR67vIGYRJK-c1L763kQx9qJY42YTYCS9rjIVXV_sP7wi1vGGQTVdHcDf0hl9LY8tg', 'android', '', '2019-11-14 08:48:57'),
('noti_tkn_1f29511ae8f0e07ee1bf2a9ff6318a30', 'cuS_WG7Ba54:APA91bFygI-Rz7U_CjTWcsJcaWrdl8j3EEX2Rp3wCY2mtgobUhv97X5MDwQhEeVFLDBDPVGDMsznAXug9Sk3UM9g61DWw5a29YM2jGrM9ACJd5ohOQRhMMSX8fDbuog4is2S95JsWSRA', 'android', '', '2019-10-07 22:27:50'),
('noti_tkn_229c4fd63ff70bb51dc2d84995b09f9a', 'cp5XsJ_THEA:APA91bE6wWl-P_bRkFLYqU6wuLHrTu_1lXYG2GII79XFaCGa5-P6CPguSiQ_8zzD80OIC_dzxZxZ1_-1Pdgu5rDdSZLmFq1Pg46_NQAj5eruBXXAMJMv65aOPnN5Daf8mZYqB3olnhZq', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-16 05:05:31'),
('noti_tkn_23312838420d201970ace2fa4816c837', 'cVXHTplBtkw:APA91bHm5Q4JvNHkzdO3a05mLhEFWb7qtgil3jieFO6l1OQGhUdRHUCsXtLBS95FFcfl0uF2BinYQZ1eKOko3WPDc5p578F_oJZK-GM16D0Ile2eCkCUuLEXDOsTnQBp90fTNOXQtwYV', 'android', '', '2019-11-01 08:10:09'),
('noti_tkn_2585f2350eeb288e45f8a72a73ba0774', 'flDaWyfIzNM:APA91bGDKLUIjkT95soMDGUc5TJBRUIfF9qhJqNTYavVwXNnUu0FkSNCtjQOrwedXp6hDzB8s4T1L-X3t9qmd0CEaU0EM3H1kwr2Y_yDMZeb_yHtvTG6ehujfGHobjvFUvoDuYjH26ig', 'android', '', '2019-11-25 11:47:14'),
('noti_tkn_2754d71a37e4e0dbb1c2688a18aae398', 'dbn47xtFNJA:APA91bHLmw9wG9CjGi1JMytmh49Eq3blTBpnaC8sCNiK9-6tYYYcesvZgTzbQ0Txss3G5-Cyg6ogXB4hkBd6JwKenLNh5elyC9Er_Z5oNZ3BoVV1pnePJ6Vzg0zlzfOP0WOzJsRj4iVn', 'android', '', '2019-10-16 05:38:57'),
('noti_tkn_283c8e70359e762cbb6f24637e8bb2b8', 'e2EDhX1QLp4:APA91bHHkK-xpCBOfJGDF6VJS1NDl4APR_CtxfE3mYdnXBZLZ8NF6MYmx8TLAgoSQK6K1Qk2oVBvJNHnmMaRMRMfc7B-2y47xOyVQS_OOTcGQcsyN_y0t9lJ1hziUxYIhxSfUwrZJFqy', 'android', '', '2019-10-31 03:10:36'),
('noti_tkn_286f75e117e86ff2603fb9dc781a4cfa', 'fivTS_KtAZI:APA91bEHh8qlyQ7l7g-LhQJZIyRrar4LdgaNtqEt7A7FEwjsRW9cERtgTU66U3xuDTYWuHxUB4BDGdMCMBDksHVTGThty2JenHoiFW8hS7XA83PMiCWAPDVOn44piVApiYM5lFi1LUGE', 'android', '', '2019-11-15 10:43:40'),
('noti_tkn_2bda52f41492652556bdad3cd93bd02e', 'd5UvCo_QY5Q:APA91bFuWn9n0rCaTc9O0Iyv_kR_ts-I_ImypKmW3Cy2iyDjH7BfLQ9UaOBYMEsMTRGcAnDE817KLwl3gaqvzXHX1JQkGDDQx5ORukSu0rWJ13L7nVSDHPOckBZhhKtksbJnRW4s6bxK', 'android', '', '2019-10-10 06:33:55'),
('noti_tkn_2e21fa236a5395f086358cf62c7d670e', 'eV2eFjTHENg:APA91bFjTp5zuvY565D6Yi-btR6bdsPbjhWsknWYl5QM4GpOJ7fsCPk90NlYdCTZ0uKtA-UWM-0sbfIvmGJpSz3KuA3dYbhJfIsYp4tlpqSQ_ryZE4TCnkn2RGLSxhd7CPOkmudcVOmO', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-05 04:22:23'),
('noti_tkn_2e44f35a8defedc084581a4cc8009d05', 'ctLGmlFVAXo:APA91bEAE-CE-yRKOpHTCdWX9YcQd_keO3RoEXCEFLozy8duzK32JoMMBCknozEUDuCKN6znq8UhbZV5vYWUvyaqzadM4b6PiG3IF7cn0W7Mf_dlyDM-OhuDOOYFg1KnCiW7iVSZKO4G', 'android', '', '2019-10-30 07:27:26'),
('noti_tkn_30ac88a576345a286d30db797940835b', 'fCIgtXQOF0Y:APA91bEMj8j7T1wa-4OpDPi4NOYV0Oqqoauvi5wTNsXCJYn8tzVDzlA5Zk7SvBKeue8FdQbr8WyGMD34ymz-sJbL2cXgYRtpM2-TFYLM-Hh012D6IYuJ56mFGkJ1DF9txpo62USLmyDg', NULL, 'usrcb9099221620a3553a5f0d80b481c063', '2019-11-29 10:13:03'),
('noti_tkn_31025e0632e7fe1dff217b03fc89381f', 'dNfShPzuvCM:APA91bGAybCCJWVWPqw90r_a_TI6NX5XIFNeTUgtK7a0x7xN2uBe_cGFIKpy6A6DGwySQIb-QuqXaPCn-jzivrOcacTUze6TcEOjm5sgI-AtzDSsRmY4D_APh4fs9VRft0KMaU37VGrU', 'android', '', '2019-11-01 06:21:15'),
('noti_tkn_333761da013d5de67a0e0239d6cf2e3d', 'fYDyOXuPPgU:APA91bFtkTI7BfmIchTj3c69yBdwKwcyUOGFz3ZGwGq_UaIQM7oBt-tYn1UIt7vsIMewJV5iC8s4v6ZWIJQ5CGKeR89svrdbJ_vgbqIo07JwanuBKq5Pzjik38JaU-rfnAXHeGBXN1xZ', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-01 09:42:22'),
('noti_tkn_34a9d416a64657e3b4ddb108cfed61be', 'eb1u3QANx3E:APA91bGGWxBH_57Se1gsk1Yf8ieUmaZiFcIohxBRoYYU2lG6GdgND8gqfTdnftOQN5JYvLAJAMJ0uYp9LGNnm11aLj0xg1AKGUB1RXvOFQFg6xT-K8bkSNCRU4uSE454s1rnDTu56yay', '', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 05:27:01'),
('noti_tkn_35a763c585d918d2191a3efc5eb63d33', 'dbYSZzTfjok:APA91bGZlnhbmEr8TOsCNJLulET3FteiTmq5UqEW-IRmQ7BFfpzAyJc5vptR2493UNnHhrC_p5h0WDGmPxlyEpDplk9IeJvC97V3-Bf7G0ZzAe-ueNDxL127CzjpKx0vvmSjuTV_hLXC', 'android', '', '2019-11-07 10:55:42'),
('noti_tkn_38e1a0fb9ad3f716c5cc6367641032a9', 'efw88T8_25s:APA91bHZIgTLvvj9zcZD4EBDAm8M-CtCNj_kSbJsyYj927v6zs5E_09xh8c3mHd9_JLsVF_3jBEYVb_iFEf0tXssH67ptdZz14Y7h3GL3fpX8K6aLdK-gBKX68BXZYThUO27WGr1m8i7', 'android', '', '2019-11-14 06:28:36'),
('noti_tkn_3aeb957e988c2f9e723a37a8f278498e', 'eic15xbYah4:APA91bEAoAZGjT-HhF2dekiTS4n-it02g7rvyKRlS_b0bQZHdLhauwiqlGzRSqswhTjNykQJco5-uG1EQ0DRGwEt4Mw2d2gB8jyZZOZsPzilqr5tKugHHDhASCTwNpyvlGEdj2hmAapg', 'android', '', '2019-10-13 11:14:21'),
('noti_tkn_3b8ab57c8dcf271f760bff16b3ad96f8', 'fbekHINbvfs:APA91bFC8HhggjI8L-veWtDoeOZ39dUiDrh8YyaImiLXAwy_2ffAlao7vnjiv9u9YtN4AVrNV1qSUy8L6_Yq0V8Sa7-P0PjjgJTZAkvVfG0zT_kWzUbF5Lrer5M-sXmP4V4QMoa3z6c_', 'android', '', '2019-11-05 03:23:12'),
('noti_tkn_3ed1391314bec53698245c2d6d1724a9', 'eLpQXT2drsg:APA91bE5bRkjvYyQUNCL6Vk0YAuHp8vBaG1SYQW3qBkxMMIIa2wuR6-aSZQJJB40RM8WOHEY-Jcd2s18vRUZfcKjPnqt-MS1vMTRqscWQgMMF2GSKZfCIEB4lBR2nqT9pvshrCHS_nGj', 'android', '', '2019-11-15 07:36:36'),
('noti_tkn_43097448d8daf588891a05198737566a', 'eJCdNRwsLjk:APA91bGXsBFBhnsIIKgV4zjhDgdDyx4T-o0NgCQAQiP1AH96txYbvoPXKpVwU4bSjux-MFsCe9pwy89uJC2Vr71pX2kxdeq2wTY6pfaDWDB5kSyzmbMALtZjl-eXyMyRZVWR8uDLOvjL', 'android', '', '2019-11-06 04:02:11'),
('noti_tkn_442e497477b70ebc07e4f64e3f1b08a0', 'eDEp1XjXvoI:APA91bG1XJYQbZJAXn7C82bjm-BMmOAMOKmmrxXsYnX50XWIFx6BflBkxhFN8KxZwL4j0JjNIKcGifwjeK40pl7GFHfkH8QcDRZmRIOCaOKGk50s3nWAIDXdGEqilH2kQygyWKIR6pVH', 'android', '', '2019-11-07 10:06:51'),
('noti_tkn_470ecac72cd0dc574e3615f263a18153', 'e8vjVl5GozA:APA91bG1BZ2hMQrokRLiSrpnsZ2hVt2wKyNPlzs_EwNtHA8pm2dKcOgHT415ICqlD3qLlDi5dg29_W-pvrMky_m_Vh7TPwHjJG5Z9qXthl9xDRbh7skvNn7t1OD7ClvRsOBIfnheMXWI', 'android', '', '2019-10-08 06:27:33'),
('noti_tkn_497336bde21edb4428c8e06b9025ceb0', 'e2MRp4KKicA:APA91bGCJ_lLtIR0Ygem5vUeTV7QhL-uV7StWDM7Li7K8ci_DOpZFw1d57YTpvJnlRLsRKTOEceXCpW5Ut6QIJIodqi65cc7HoLHotNVwBkuLp7h0yoHUgbEW5Owgajs4h2MWYVXkYZx', 'android', '', '2019-11-15 09:51:58'),
('noti_tkn_4a9318b25d1dead22c9fe983cdff4f82', 'fYDyOXuPPgU:APA91bFtkTI7BfmIchTj3c69yBdwKwcyUOGFz3ZGwGq_UaIQM7oBt-tYn1UIt7vsIMewJV5iC8s4v6ZWIJQ5CGKeR89svrdbJ_vgbqIo07JwanuBKq5Pzjik38JaU-rfnAXHeGBXN1xZ', 'android', '', '2019-11-01 09:42:10'),
('noti_tkn_4abf9bb36ca329cbd3403430887efd53', 'eJCdNRwsLjk:APA91bGXsBFBhnsIIKgV4zjhDgdDyx4T-o0NgCQAQiP1AH96txYbvoPXKpVwU4bSjux-MFsCe9pwy89uJC2Vr71pX2kxdeq2wTY6pfaDWDB5kSyzmbMALtZjl-eXyMyRZVWR8uDLOvjL', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-06 04:05:17'),
('noti_tkn_4ad901a67a1d29ff9556261684ca0192', 'epyevavrVFQ:APA91bElQbhQ0_YILVcHSidVsqgR8YvTUqUYLrNdH5Zi1vBi-7EG1au6_gE7wlUHfSiwPgkk682DeNCzdGKB43lR6cQfjIJwA5wpHYdiD_I6KxbgHcW1Div8SgQs4F9oVQfPCbNqpxKv', 'android', '', '2019-11-04 03:52:47'),
('noti_tkn_4b1ad7e0c84a38688625800421388e6c', 'cLXnLx0nwds:APA91bFikAtWXYF0e3yhdLdLLqXQT7mQ2R4-AUpCo-MNcRvTq9Z-QWPgEGc8IwRm5DbTcIvp1V-pe2h8l60KjfJzvMBNTlDjPIY8Tx6Q7xtghwxVEtSuBnOOThnT2vSvk7b-KvH4C8Ry', 'android', '', '2019-10-30 10:13:44'),
('noti_tkn_4bbcd1eac59f9170b17a28ab3142c225', 'fg6_IkYqYvM:APA91bHiCOg7A4jh9cp9voOzlepM-SYKTn4SlZAeusoAhnkK7xO1G2xErhf0upJGPs8TrBxkjE7rjJJNOPNyenuBSnPlGc48lz-pxjG8hw9QlAVvRwpeyTNFKM1O_2WxrHlLOAgmHIO-', 'android', '', '2019-11-13 10:33:02'),
('noti_tkn_4c55df08fdb61d9432a7844497820441', 'fAWTmJCg3mU:APA91bFr5bplXGksp1zdOhy8VyYLu_LP7AB5-vo6J6zovz_Ql2zH7HHBd0YzbTepLame_xLvT7AHA4OlxToz9Go3n0isRChN61A7xdr2AiBwa_CyN1qG9QKBnizoT2eJ9eUHXW2X6Eoz', 'android', '', '2019-10-11 20:53:44'),
('noti_tkn_5004e97a73947b9740f993cddf069c28', 'cs0jCGh80BI:APA91bEic7B9nUBPDjRvLsKBNTLWi6MjxJ9K9qPye5wmPeJvC_BKuc7av6CtzUlQvpKpFIcO7spNUEbDeWqGtojvYoZZA_1s6yw2D8PwN9KEAG0UtPEV_tuTynvvIKd0AI_K3a9fRx5I', 'android', '', '2019-11-13 05:11:22'),
('noti_tkn_5075d8c69cf748113aafe67e0e37dc8e', 'fs-6rBqSR7c:APA91bGrpx0Q9YXP4opO2MM14OSuuhzj7h36d-KH341Vj2_IG3-MEk21VXVhzYC2uomUfVHokY-vsKTIrvjVIS--KZBEoDW0pr0jSpITnzFriVVF0cQjjKgO-k7_aoUQMDnFqzxEwYUG', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-01 05:29:41'),
('noti_tkn_52533aac68ca674bffb2c59902015984', 'f59Uy645NnE:APA91bGcNI3hHhb5wIvGwK0W_F0KHJrZbXXts3FfOP0JDGiqk9T1fwrawlBS73UxTVQLJ1Y6BY3WpJB1F7KDFvmOU9mnVgeZb_nc0YEjEtBFZ1X_0nIQYKsylpuGxIDB1KlibfAnes1s', NULL, 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 07:38:25'),
('noti_tkn_54f030ad67490680261590926dc4e546', 'caaTYHokeOY:APA91bEMcigTIoeI_akPDwpBKL3SUPdHFRopuQydz87aMXxiu8Aup767SPkj_YNOoyNNF9LLvSwUcUHqe6AHFlQqpb8eAvjGCn49gdcJ7UKQnnXNIDLFj78B6hRVkZ9z0sPi5Er1YXjZ', 'android', '', '2019-11-15 09:18:20'),
('noti_tkn_550ab344d44c4ffd7fffa77b07646767', 'nodevicetoken', NULL, 'usr786aa1c24816cf370ecd666472d8b548', '2019-10-15 14:32:55'),
('noti_tkn_5546ec1ff57d03e8646cf4091f093108', 'cMmVESY9McU:APA91bEuuk93DAPr_4smxElwwSZw6U4s6eGJPYRAj1YDpBs4RtD2Gz9YwMdZ2begXarNlTrlN5A95yDbe2akqtIvFvpT_ZzyXIj0hQnvnyvkN5Qz45JMakxkYlIzQNjXIWll8iqmIflQ', 'android', '', '2019-10-07 07:35:35'),
('noti_tkn_557ff58767cbe8718f00e215350da6e0', 'e2EDhX1QLp4:APA91bHHkK-xpCBOfJGDF6VJS1NDl4APR_CtxfE3mYdnXBZLZ8NF6MYmx8TLAgoSQK6K1Qk2oVBvJNHnmMaRMRMfc7B-2y47xOyVQS_OOTcGQcsyN_y0t9lJ1hziUxYIhxSfUwrZJFqy', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-31 03:37:18'),
('noti_tkn_57b6a2ec8e2afe995825bb5c6acede64', 'e5sBXd7TY8M:APA91bHpWUbf59vwweFWkrpPtglSnNKTBrdKPc5jtI0tt8XK2Fxy1_5gcoUBq2O3NiTgXJNCnoNX14EM6ui46JmzB0UvWX7QArOrvXNiYfbvXbpIFC4oJ1NDuy-dqNAbA7NMnO0DNTrF', 'android', '', '2019-11-15 09:24:12'),
('noti_tkn_5bf3a972b58fa7e20cbd2b2b7a79fd51', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-13 11:22:50'),
('noti_tkn_5c7afbd48f5ec2e4e629f2c76f22a7e3', 'dbn47xtFNJA:APA91bHLmw9wG9CjGi1JMytmh49Eq3blTBpnaC8sCNiK9-6tYYYcesvZgTzbQ0Txss3G5-Cyg6ogXB4hkBd6JwKenLNh5elyC9Er_Z5oNZ3BoVV1pnePJ6Vzg0zlzfOP0WOzJsRj4iVn', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-16 05:40:24'),
('noti_tkn_5d557ea11a4bb3ff100c74dcd4cf1bb5', 'dNfShPzuvCM:APA91bGAybCCJWVWPqw90r_a_TI6NX5XIFNeTUgtK7a0x7xN2uBe_cGFIKpy6A6DGwySQIb-QuqXaPCn-jzivrOcacTUze6TcEOjm5sgI-AtzDSsRmY4D_APh4fs9VRft0KMaU37VGrU', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-01 06:21:47'),
('noti_tkn_5dd8e7e055c506e98d464d3e26cd108f', 'dvesKky1Tug:APA91bHsij5DhNOK9LW7_kOCHy4ORBkstjBUY-aoES2wNrvuWhlbD4WPwJbPSCYve7NcKnqc9-xp0rslFJ5fJZjYUBrXwBaod1g7-cHjjNVfUCfya_TlWUITDaYQw-q4-myQ2lhLKfS2', NULL, 'usr7e1473b2561c8c5a5b2fc2a0b4c3b732', '2019-10-18 05:05:55'),
('noti_tkn_5ef4e2e7c06e75c6ab0e2f87597c9da9', 'dZjQIjciL8I:APA91bHWo_G905pnhwzUUZoysmCEeY5xi4qzeUyxiZTIYZ5CHLc1lN6UZl8BZK5H8P0BR17A96MYCJkMFl0GLhDCSBU-13zLo2nbhdujK53MpfWN3KiDnHdoDI0vF5dIga0xGSNcKFDb', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-09 06:00:38'),
('noti_tkn_62b304a8af502060bab01f3d403df687', 'cuS_WG7Ba54:APA91bFygI-Rz7U_CjTWcsJcaWrdl8j3EEX2Rp3wCY2mtgobUhv97X5MDwQhEeVFLDBDPVGDMsznAXug9Sk3UM9g61DWw5a29YM2jGrM9ACJd5ohOQRhMMSX8fDbuog4is2S95JsWSRA', NULL, 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-07 22:31:04'),
('noti_tkn_6323ad5a569e1a76610ea2f851d09048', 'd0P0A71haJg:APA91bGj9GV3Dy7ETyFWvC9TA6o-HlTA7dQnHGJeaF9MhWFACQGILrrzEVWyi31w3I7ubVSKdB0mg8lcb_K39V3LoRxnjBD7_7PaLn7Lrc50YC7TNpojWzWj3YdM3vdegQywZLVmDAyy', 'android', '', '2019-11-13 11:18:10'),
('noti_tkn_63426fd9817f23ff73d2a848e59ad728', 'dIwU1Dm4TXo:APA91bHYyOH2dXeiBVzOaw3e9CHhT5mFtNHSID6ZfDb9Fe5D50q9WcqMyXQl4ZJxNxAPPDOUK1Gr1YaMeBBwjRevWq02IOVIz3m4GLUoAaCaE5W1By4gwuKPk_UPHlFzpZAMHJOUkWWz', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-01 07:00:07'),
('noti_tkn_6466db894afcb1fbcdb3445b4ebbe635', 'drPtk0cipHk:APA91bEad-5M2tBD5quzki0quT9VUgS8Rd9HPbzUOPkg1t3MvNlxLKUfzmN2hl530-LowDOy5OXTf2VdTgCSC_Bo4v18l4ez5pE97m5pBpdprwwnwz9WOqR0OPGLM_wCsLkkTqwEdCqL', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-15 11:22:01'),
('noti_tkn_65d1a26c79e3e7659e2a6bba4e4d42a2', 'nodevicetoken', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 16:03:20'),
('noti_tkn_661e3c7f1d489694d4d1fad6fd623d13', 'd74SNmLqsQo:APA91bEFeVyDskUrOP-cFY1Fj354z4G3im2YZxWis-ztIo5gkpDT1j0kqCWe-YTo2xFpim_XPSzYxIB0q_IjN_jWf3hC-zXf1hla_v3ZE0d7tzGvjICdaIfuCl0BNEvVZxVFEQec3tFW', NULL, 'usr71ea8cc1c98df13e45d4bbbfceae5f38', '2019-10-10 06:27:11'),
('noti_tkn_6778c8a041d4a1b970e5c3cb14da3339', 'dSvI5S3Ucew:APA91bELp0tRm3MYvTKLxFDmME2kLuKywLg2qvgv37h9tckSAbma9ZZJFAGplH3Q-lGGkDTfmsifuI81kqylEkOAO15FgIp3NyHPEwVJ_Gw6mMJA0J6dJkIsR2Jq1HpQQ4DAzkqCP3P6', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-31 04:29:01'),
('noti_tkn_67b609a084fcd9a0d2cc799c1280dbde', 'eGYLctgEC58:APA91bEoG0h_7Sl-_FZPi_q9b8bpqL2XeV5E1g2aPXei61IUCLeKEwYS86aE8SMesZrYB7u7gAhwfy0PUCk3xeotRnFUST3auHKTvZO6GJEUXe5O5L7olBCs042NVZEnKm38KeExIaIr', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-04 04:01:05'),
('noti_tkn_6975a2063e232f3eacc2ceadce484697', 'd1ClfOJyfsg:APA91bG4wkgaatnNCSv1uqaYkpLTZZaPqbJk7VoqkQzQ7bvLDfAo4tC2dtvz9fidIAdMyH81TDMvYBkTYtcwjiOod04Egc70_IPqjREHxYuiflLco5b3Pz_isS9TYAE3f1UaOFEQKrCw', 'android', '', '2019-10-07 07:48:01'),
('noti_tkn_7056039adaaa05bef96b6b9bf9192f99', 'fAWTmJCg3mU:APA91bFr5bplXGksp1zdOhy8VyYLu_LP7AB5-vo6J6zovz_Ql2zH7HHBd0YzbTepLame_xLvT7AHA4OlxToz9Go3n0isRChN61A7xdr2AiBwa_CyN1qG9QKBnizoT2eJ9eUHXW2X6Eoz', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-11 20:57:36'),
('noti_tkn_7377f498a2835ba2e56ae3641657fe59', 'dTfmY0bM0cM:APA91bHNOJXUU4xDkTWv1_0KnD2NEass5wmkpQJ7lDn1N50kFxGswvVgzV5QS3w6DX28xoINusMaWRYvdxW8WsU80jAd8lk6XVdddQoiolIEfkqahyLHuV8Mo49ozg6CkKyMGUP9Ravq', 'android', '', '2019-11-06 05:37:47'),
('noti_tkn_73faaed6a716fbe2ddb48ad07b6b932f', 'cMmVESY9McU:APA91bEuuk93DAPr_4smxElwwSZw6U4s6eGJPYRAj1YDpBs4RtD2Gz9YwMdZ2begXarNlTrlN5A95yDbe2akqtIvFvpT_ZzyXIj0hQnvnyvkN5Qz45JMakxkYlIzQNjXIWll8iqmIflQ', NULL, 'usr9f42abfc8638a5e733125a12f37bbf2a', '2019-10-07 07:58:15'),
('noti_tkn_7b950de8af0bebf265ab5594c92a1f54', 'fU0q8DnyOHg:APA91bFO4sT8UphR1qJf8a1-TIOx8UXZqCxA3B1UqYUwljJab6hIqBdiCYKAc_7RAzCBDWIPBly5mbwjIyAKGzUqChdEJOq0MGNqYbB3Qjrur8eLdQICqCIUlneJo6HZVeeQE_tlUXv4', 'android', '', '2019-11-15 03:49:42'),
('noti_tkn_80b98665b8d704b835180468d109905d', 'eIArY0lRaH8:APA91bHElgFEWlURu9VNVfEWBhzIaXZx78ZUWBC4VHY9OYC7irJOmL5EcMci-QA7Zi5VW5BL4xCZ5M0Fy0ELwjQwIJ_83htPkepdTMq8g9f5R0oznvH6ezpvnEmqaXPioP3gbxfg2FPS', 'android', '', '2019-11-25 11:20:40'),
('noti_tkn_818847fe4b20d03bbb04042630ba7ca2', 'd3C0xITcSp8:APA91bFeuFlOhEsE__gwTYI2_MkzAw4h1iyf9ATiFHqfJ14hDUTYO9QJj0FXkwVHvKcgxxRqE1vYb31aQknjAfjCmWg8W6AeWZUY9ch6ZOyuZI13nFZIa0bUHxLeeKOCwm5Ms7C6-Z02', 'android', '', '2019-11-13 09:50:28'),
('noti_tkn_81f974b2feed6ce5bc680933edfd690e', 'eshPmoH6098:APA91bGlFmGYdER2OG8_28YNU9VezTcdaA3ylPBO9BbPlxN08R56F1WFnd0Kt2Uyu6S8sw3qTDCT9tVLWfjmVghjcpoWwkSZ5Zi48KFVFtMC8fnsBKMre9Tpb5jZ4YW9l6zTbOrAyCB4', 'android', '', '2019-11-15 10:39:10'),
('noti_tkn_867c9bf47e6cc50e0dbd86645e49f06f', 'test', NULL, 'usr8dad6e9376480737a9a725e5dab8c402', '2019-11-26 05:03:16'),
('noti_tkn_8e7cef1d47fbbff9632ae2a2ef46c4cf', 'f59Uy645NnE:APA91bGcNI3hHhb5wIvGwK0W_F0KHJrZbXXts3FfOP0JDGiqk9T1fwrawlBS73UxTVQLJ1Y6BY3WpJB1F7KDFvmOU9mnVgeZb_nc0YEjEtBFZ1X_0nIQYKsylpuGxIDB1KlibfAnes1s', 'android', '', '2019-10-07 07:33:51'),
('noti_tkn_94ee74492bebc45f5fe4df0949a39b76', 'fNHe0t51clY:APA91bEHwPt805D_cvUiMzerT8HSCgC2SD-6Ggs_hObM0rUw0ZyrQIabJZy4-ZQs7bmkzsBgewE4lTb7VcMKBM6y4xKmTbBKHZrptOeDO2Xyrzku8IY3uv9On-E3OoTy1EiQFrlzE2vB', 'android', '', '2019-10-30 09:47:08'),
('noti_tkn_95b64c6166da0c28076ca0733d5a69aa', 'fivTS_KtAZI:APA91bEHh8qlyQ7l7g-LhQJZIyRrar4LdgaNtqEt7A7FEwjsRW9cERtgTU66U3xuDTYWuHxUB4BDGdMCMBDksHVTGThty2JenHoiFW8hS7XA83PMiCWAPDVOn44piVApiYM5lFi1LUGE', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-15 10:45:14'),
('noti_tkn_95e53e6bbb91fe2136fc5baa86d924e8', 'dW3MwVOcaV0:APA91bHHaTju3cPUTGTTr1H8fG4NtpW-hfCJfn3qGby6JQINMtwXqfHtoAtBztRGEvj24rUct0MlALSL0TwNsszjnvn8lhmwWXoayqKVPIQ6vhTnb7FVVERb-xgh9ONb484kR-SCTk3F', 'android', '', '2019-11-13 04:41:43'),
('noti_tkn_960f0983a0ff0efd5325eb04a1f697f4', 'eDEp1XjXvoI:APA91bG1XJYQbZJAXn7C82bjm-BMmOAMOKmmrxXsYnX50XWIFx6BflBkxhFN8KxZwL4j0JjNIKcGifwjeK40pl7GFHfkH8QcDRZmRIOCaOKGk50s3nWAIDXdGEqilH2kQygyWKIR6pVH', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-07 10:45:00'),
('noti_tkn_96ff207518d67eaa7eb2e2a8a742252b', 'eEiUJXtIQFI:APA91bHYCShXSl3ZnnqT1iYFQ0bBcg4g5aSeeZsPBh0rS3At23EhpZM3fQyWJKAv78s_bWxOOqlPwTWoJJNHgvtcecyoAcbei_N9X8yqiryDOZB6zGUmFiYDRcyVoY894Xe-SNa7aeEi', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-01 10:59:58'),
('noti_tkn_971f72c5ffc6ce41905baa4d2532c675', 'fbekHINbvfs:APA91bFC8HhggjI8L-veWtDoeOZ39dUiDrh8YyaImiLXAwy_2ffAlao7vnjiv9u9YtN4AVrNV1qSUy8L6_Yq0V8Sa7-P0PjjgJTZAkvVfG0zT_kWzUbF5Lrer5M-sXmP4V4QMoa3z6c_', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-05 03:24:38'),
('noti_tkn_982acd63b1d9f843dfa1f6d8e5f17b67', 'd1b-hpL369c:APA91bE_5xRV3nazmiXRJC_4mKXKFLjqQBGPkB_rnDxrwjo1m2KveLW52ognqRMU3ISCVEzdpRVkX8pGkmHepAMez2eSz13Ea4gNnf-WWOEhSvy4o4PRdLknNaIbDXxpqi2wANmF9yz7', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-18 06:46:59'),
('noti_tkn_9a4171382116a7a7355998f2d6b966bd', 'fw9IgZSWEPQ:APA91bFZgc1x2DORHG_KI3inCqgPFf3fCVzKsApZ2fYtGLQsQz_n-CaH-cIxGxxZ5w7M3ZJx9G6_6PWfC4y7gbJ93piTzY5AJ5i7zv8QW1cL7KoyVvZ3_sHzUlzNdb5nXUmIcarV-v6t', 'android', '', '2019-11-13 04:27:47'),
('noti_tkn_9a6b3a9572df15d421fee1674b27586a', 'fKVPtwaUDsA:APA91bHlOfbgY1-s2PPCakLv28hZGzF9_RO6IKT0CmfWtvBJiVUS83nrDo2zKaMhikzr8IdjqgA2bPlJFGmavFEiII7wnyd-MxmPY9O4ueTSgcx8tDTc-DNRi8whnorB9D8nMhBUJRir', 'android', '', '2019-11-04 03:16:07'),
('noti_tkn_9fdeebd6b7f2ca7d0cb17ff193585286', 'dTfmY0bM0cM:APA91bHNOJXUU4xDkTWv1_0KnD2NEass5wmkpQJ7lDn1N50kFxGswvVgzV5QS3w6DX28xoINusMaWRYvdxW8WsU80jAd8lk6XVdddQoiolIEfkqahyLHuV8Mo49ozg6CkKyMGUP9Ravq', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-06 07:11:00'),
('noti_tkn_9fe1b2350b358af73a3d00fbee3db6bb', 'e6olJaGbB1A:APA91bGjoh7u_j5sTA88lNblApzEExvuLIsMg6j-9LzeOpEZgzb1dzo_9el7lT1xCC0e6fLY-NoZTQvu_FwCeNlqfTn2xBWTyME5vjTgmRnUJoOXG5WrGoivt9iJN7unc1pG92nfJRJM', 'android', '', '2019-11-14 05:21:41'),
('noti_tkn_a43e25d7af665f558459447651104e86', 'cz8bLo5NFSQ:APA91bGjDGZQr0hW2SSPCrvSGK6s2ekKsBITx8sZhmM128TTGG9TRsjY6W8qERu4cDGZC0VYHKtd6xxaBRSpxrsSMt-SC9UpYmD0p-oiVJMyvQKFzu0O00v7WPYRFFjiy8rpLWWTCIuv', 'android', '', '2019-11-01 11:42:07'),
('noti_tkn_a84d7dee2af0b8d81f3c408047865ade', '', NULL, 'usr24e910fc4f5971972d10c8a0a2bbbb23', '2019-10-07 10:31:32'),
('noti_tkn_a8ec5c67673529ff41aad9c9fb4596e6', 'e1LpAfO0-yc:APA91bGxXj7gmTTr0Z0b9Xn4qUpNwz7G1I7gl63pkUBsyfA5_eVdkZEIVJAfLxsm95RpLpXFghRGzaheg5k6qoC9O9GU9qHt4AYQEgpL4qlLXw7-jt3-oh_mxI0QQXJ06h0VZMoLwwmy', 'android', '', '2019-11-13 03:10:59'),
('noti_tkn_a98d385e7b86f5916fd8c3279cde9fd1', 'fs-6rBqSR7c:APA91bGrpx0Q9YXP4opO2MM14OSuuhzj7h36d-KH341Vj2_IG3-MEk21VXVhzYC2uomUfVHokY-vsKTIrvjVIS--KZBEoDW0pr0jSpITnzFriVVF0cQjjKgO-k7_aoUQMDnFqzxEwYUG', 'android', '', '2019-11-01 05:29:26'),
('noti_tkn_afa62dd2652b4345107b4b5313ab3aec', 'dH0FKoiO4YI:APA91bERehXmI7A04IA_9ouMP2vL2CcUO-NUpgyVRp6uHwAgt_JcvEx6kl_IJIKWnXjobL4-KjAxu2m1SB2e36H6hArzdV6VplUKG4lAGu4p8f5NoDgah-4IF03HirEtT24wlPzWJmDK', 'android', '', '2019-10-17 11:38:20'),
('noti_tkn_afedd718161981e156b5fb2b7dc05198', 'etmMmKW2KoE:APA91bEcYIK4tGP8JtNzrdNBatu34PESu2rvnQe0hVAxKe6VyKa5EmuNRnjlDXcfMSqnMwbGFnRFD536w_OignbDEgE51KWUH536A4MHUuXKBUA6NG40WxGPPVoiOBpvtr7NK026Sr3y', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-12-12 05:31:12'),
('noti_tkn_b0e34f6a0c38df16603de01e3d06550b', 'ff5DVg4KeR8:APA91bEEyeKqunDy_iHcbc4ZXci3TCtbZBE51M82e8jsMIfUrZwKaqQ-plGHg6tXnx7Ple7LFauRZASw4EqUvXasU_gwoCyC2QSU4hgKNiD0ggObukun0H-SZ7RFmxVLZal2eH3SLFwh', 'android', '', '2019-11-14 06:31:14'),
('noti_tkn_b291098e263241c5fe552727369619c7', 'drPtk0cipHk:APA91bEad-5M2tBD5quzki0quT9VUgS8Rd9HPbzUOPkg1t3MvNlxLKUfzmN2hl530-LowDOy5OXTf2VdTgCSC_Bo4v18l4ez5pE97m5pBpdprwwnwz9WOqR0OPGLM_wCsLkkTqwEdCqL', 'android', '', '2019-11-15 11:17:45'),
('noti_tkn_b3a5ad1d0f4755d7b2522df606c0fa07', 'ejabp_qTEAE:APA91bHJaR4DbIYRLOP2wqmdJK0sYkWdTRlMDKqvnNAfYj7sI40wj4Ui1jpxVtdn_tpkXXAjfc5gxzfPLsZ8H8rS6GO6HVrLdKY3TZM7qWm-fD3Yf5yo7pQIo9hGX5HS4SPqLRpnLDUN', 'android', '', '2019-11-14 08:26:18'),
('noti_tkn_b8e57ec40ebb42739fab6128387c7ba1', 'd74SNmLqsQo:APA91bEFeVyDskUrOP-cFY1Fj354z4G3im2YZxWis-ztIo5gkpDT1j0kqCWe-YTo2xFpim_XPSzYxIB0q_IjN_jWf3hC-zXf1hla_v3ZE0d7tzGvjICdaIfuCl0BNEvVZxVFEQec3tFW', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 06:57:49'),
('noti_tkn_bef63f6779120bc6471fc260c302fffe', 'd1b-hpL369c:APA91bE_5xRV3nazmiXRJC_4mKXKFLjqQBGPkB_rnDxrwjo1m2KveLW52ognqRMU3ISCVEzdpRVkX8pGkmHepAMez2eSz13Ea4gNnf-WWOEhSvy4o4PRdLknNaIbDXxpqi2wANmF9yz7', 'android', '', '2019-10-18 06:26:29'),
('noti_tkn_c002f137b7a96500c4b2bf77f3ae985e', 'erSBqMOK04Q:APA91bH6NPUkS8QXDXJFIWo9MkjtDbeafArzbghwr55LGtq01ZDgNBXXE-4kUa-kThl6N8Ls9-o23Y5ozNnVS4Vn-w-BNnpkm2jCCumweXq_iGR6HG5bdixUl_0vNnQe3iLV2QF2lTO0', 'android', '', '2019-11-13 04:11:26'),
('noti_tkn_c30542c6ddd181e624556d37aa5e4c9e', 'c_0vDkSzTak:APA91bEgxCGDpjDnvg3JX0GlWQ1HyEDlImqKtrX9var9Dv4iNX7UZBoVFkQRD8_szIXVHDRZ2uPrSNOGPZ2f8ULGE0JwYTqSvQ2tOPiRKTpqhjxyuoiryHbAxFqsVAxAxPgmuAmGyXhJ', 'android', '', '2019-11-13 04:57:13'),
('noti_tkn_c62ac56462cc2f723ec41c6733201b1e', 'dbYSZzTfjok:APA91bGZlnhbmEr8TOsCNJLulET3FteiTmq5UqEW-IRmQ7BFfpzAyJc5vptR2493UNnHhrC_p5h0WDGmPxlyEpDplk9IeJvC97V3-Bf7G0ZzAe-ueNDxL127CzjpKx0vvmSjuTV_hLXC', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-07 11:28:22'),
('noti_tkn_c832d5778c2d588f7581adc74b4a6519', 'f90YORXUPZ0:APA91bH8s8ZBzvn8qThq0Voda2MSQ9g69sPygf3_0rZhO_bbRAjUBvLTfm5kEyyyv-jO9e-lynM9xVOF4md976szQaLpjk4LUlVYHLgZQ7HaC8nP5jI-aMvoiqLM5lXwxWSsSkGf1fZr', 'android', '', '2019-10-07 10:27:59'),
('noti_tkn_cc5547aa009b1bafeaeeff70e3ba9d8c', 'cNsysSupoqA:APA91bGiL71ug9iEF2Mm2kgQErWZ5ykUbwLJrUHCo65PBnbeIVkW-_rx2Kgxz287wea1hvWXawU1ISx-xlapxiY-Q-9lkqlGl4h2Z0iK6MVr2__BJWgTIYTt-NYrbxOiycx6mr5Fu5Ui', 'android', '', '2019-11-14 05:23:13'),
('noti_tkn_cecd55d306fa21119aae1c9a87956a7c', 'cVXHTplBtkw:APA91bHm5Q4JvNHkzdO3a05mLhEFWb7qtgil3jieFO6l1OQGhUdRHUCsXtLBS95FFcfl0uF2BinYQZ1eKOko3WPDc5p578F_oJZK-GM16D0Ile2eCkCUuLEXDOsTnQBp90fTNOXQtwYV', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-01 08:10:50'),
('noti_tkn_d2e87aafbf0eda07c2e0f6e5dff78991', 'dZ2zslVfQW4:APA91bF6JIBqIQN-Tmt_7leGGYJPhWHNPtdLLA4Jh9r6rrLqoB3S9yy1tkqBN_0tvHEas6glqcnkv1b-RVWg6L6IU4C-BAAb0ccEMCDp1p9t4oTs2SCM5F7KR6dDDxEWNqGIfWvpSu2E', 'android', '', '2019-10-17 11:10:29'),
('noti_tkn_d49856843bf53387d099cb536350e0a4', 'cT7KJ-zqrp8:APA91bHLFyJZcR-8GioaU2MfA2sPvWH0-GF9516kgMjoUFyyMrenO70Vp3khrpzS1J2CfZukzaTDxqyzF8eKftFItnSObfKlrHtVhSXWtjtgeW9bHXo6E-KgFAhi85U92IjGYDP-Qqsg', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-15 10:58:24'),
('noti_tkn_d5f7ce46e4f8fec0efe9b73e7e412fa1', 'cp5XsJ_THEA:APA91bE6wWl-P_bRkFLYqU6wuLHrTu_1lXYG2GII79XFaCGa5-P6CPguSiQ_8zzD80OIC_dzxZxZ1_-1Pdgu5rDdSZLmFq1Pg46_NQAj5eruBXXAMJMv65aOPnN5Daf8mZYqB3olnhZq', 'android', '', '2019-10-16 05:01:23'),
('noti_tkn_d66baecf9de219a9936b5bd9a44e473b', 'cz8bLo5NFSQ:APA91bGjDGZQr0hW2SSPCrvSGK6s2ekKsBITx8sZhmM128TTGG9TRsjY6W8qERu4cDGZC0VYHKtd6xxaBRSpxrsSMt-SC9UpYmD0p-oiVJMyvQKFzu0O00v7WPYRFFjiy8rpLWWTCIuv', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-01 11:42:25'),
('noti_tkn_d8efbdfe73f5b19bd64ce77e16afb5c4', 'etmMmKW2KoE:APA91bEcYIK4tGP8JtNzrdNBatu34PESu2rvnQe0hVAxKe6VyKa5EmuNRnjlDXcfMSqnMwbGFnRFD536w_OignbDEgE51KWUH536A4MHUuXKBUA6NG40WxGPPVoiOBpvtr7NK026Sr3y', 'android', '', '2019-12-12 05:30:44'),
('noti_tkn_d950f80ab663c8c8f82061548fff101e', 'dZjQIjciL8I:APA91bHWo_G905pnhwzUUZoysmCEeY5xi4qzeUyxiZTIYZ5CHLc1lN6UZl8BZK5H8P0BR17A96MYCJkMFl0GLhDCSBU-13zLo2nbhdujK53MpfWN3KiDnHdoDI0vF5dIga0xGSNcKFDb', 'android', '', '2019-10-08 09:57:27'),
('noti_tkn_d9e5a9ab16c7e0cda046d9dd7bcd815d', 'eDHc9OEhSd8:APA91bGjszeNRezGcRp9s7jVz55YeyY-ORJos5uTtSdqK4D92qQid2gdysGhLGR8x66AqDn4tSuViukZ7zND_WE8YP9FCOVd_VA4dzWxlA4wpgn5q7cZonjbfRm0ERAZi9m2gUsS7bsv', 'android', '', '2019-10-07 07:05:34'),
('noti_tkn_dc10deb0e7fe30d919dc596fdff0af4a', 'fs6WRWrjP_c:APA91bEBKaToIbqZOnQTddhmLQBl1ukpwr5-rL-EQs_Vx6KielNFPNcVvFyhvdUla-mqkwhYf1X-HtCMSA5KdTgXFkpFirYiym3d_tWsghsbXYzjl9h8MWhk_Uj39m3du3V1_OTl0IGI', 'android', '', '2019-11-15 09:38:31'),
('noti_tkn_e17649b7107fbd7fff15829370969c3e', 'e6xtq7TwMhA:APA91bGICnAZ22X3CCEXhup80lORgBeGJEzjxOPnwRHn2QY8s8qkdtPWMRufKWbgYpPukWnoQMTsDM0suMWS2O8pM4FbZUJ96Crc991naXNVwUY0WE3zuKvo3suyfJG1dGA1OS6JaMxZ', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-12-12 07:01:18'),
('noti_tkn_e3337fb3e348a2be18056dc05a14862f', 'eic15xbYah4:APA91bEAoAZGjT-HhF2dekiTS4n-it02g7rvyKRlS_b0bQZHdLhauwiqlGzRSqswhTjNykQJco5-uG1EQ0DRGwEt4Mw2d2gB8jyZZOZsPzilqr5tKugHHDhASCTwNpyvlGEdj2hmAapg', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-13 11:14:33'),
('noti_tkn_e60bffab783b923d9174a7d5af6a3997', 'dZjQIjciL8I:APA91bHWo_G905pnhwzUUZoysmCEeY5xi4qzeUyxiZTIYZ5CHLc1lN6UZl8BZK5H8P0BR17A96MYCJkMFl0GLhDCSBU-13zLo2nbhdujK53MpfWN3KiDnHdoDI0vF5dIga0xGSNcKFDb', NULL, 'usr7aaab7b5074333e569e1725ee7645bca', '2019-10-08 10:00:39'),
('noti_tkn_e90fcdf6708b384aaa9ab3e4e5a12d26', 'eEiUJXtIQFI:APA91bHYCShXSl3ZnnqT1iYFQ0bBcg4g5aSeeZsPBh0rS3At23EhpZM3fQyWJKAv78s_bWxOOqlPwTWoJJNHgvtcecyoAcbei_N9X8yqiryDOZB6zGUmFiYDRcyVoY894Xe-SNa7aeEi', 'android', '', '2019-11-01 10:59:40'),
('noti_tkn_ee88cf39cc97c133ca70065f8f0dcfe3', 'dQ7Cvu5mCvU:APA91bF_9rjtinaxKibETFMkiujZoUWPqbWOJYi4_eRBSAc_kKcXDmDH5nqVS4-Qkol3ZZ81g8GC8VJ6smwR7TstP1OwVt-AVh7VsuvU9PnKxyvElds5zML2pqsmrd8GnMtzZu0utDKp', 'android', '', '2019-11-08 03:14:30'),
('noti_tkn_eec8e263ad924385376be62f222520c0', 'eGYLctgEC58:APA91bEoG0h_7Sl-_FZPi_q9b8bpqL2XeV5E1g2aPXei61IUCLeKEwYS86aE8SMesZrYB7u7gAhwfy0PUCk3xeotRnFUST3auHKTvZO6GJEUXe5O5L7olBCs042NVZEnKm38KeExIaIr', 'android', '', '2019-11-04 04:00:57'),
('noti_tkn_f141f838115a99f23effd81693a25aed', 'e2MRp4KKicA:APA91bGCJ_lLtIR0Ygem5vUeTV7QhL-uV7StWDM7Li7K8ci_DOpZFw1d57YTpvJnlRLsRKTOEceXCpW5Ut6QIJIodqi65cc7HoLHotNVwBkuLp7h0yoHUgbEW5Owgajs4h2MWYVXkYZx', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-15 10:42:10'),
('noti_tkn_f4349315bf5b55bdfb784cc3cb4abd13', 'e6xtq7TwMhA:APA91bGICnAZ22X3CCEXhup80lORgBeGJEzjxOPnwRHn2QY8s8qkdtPWMRufKWbgYpPukWnoQMTsDM0suMWS2O8pM4FbZUJ96Crc991naXNVwUY0WE3zuKvo3suyfJG1dGA1OS6JaMxZ', NULL, 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:03:33'),
('noti_tkn_f4dd40ca34eedf2d4a1351bdbe24fd4d', 'egl8ozHTYvg:APA91bE03ykKqUn04HGMBGxlUKpIPey9iYecpBReaIRKTOZApMmVMCQ9vXXXM3mt2tlFcP8rdP3ZxyWAevki5P6QcZm-VaxCeLcy_qkHoMlvpMuuu9-al6CrJz72oPC6RfAxMYtXDGN1', 'android', '', '2019-11-13 11:42:45'),
('noti_tkn_f558acbd22aa070f03d8debf18911979', 'chiSjsuq2yw:APA91bFyo3e-qHyzuL_V801iX-HCTG242PrdQYdRSP4eVOlo4LE_5MQgrgKLpWXWkUIo_rUyC6jxHd44uhxJWVfekE9ySZeIt9Vmjl4MDPMn7nTqLhTxmNok73nZw5XlCnEcklazHUNq', 'android', '', '2019-11-06 03:57:16'),
('noti_tkn_f5987ca02b952fa0007f4974885f50c0', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', 'android', '', '2019-11-13 11:22:24'),
('noti_tkn_f753f62aed6166a4b7e5ca34aa038c34', 'test', NULL, 'usre16cb198d292bb9fdde6f2689a00f258', '2019-11-26 05:21:53'),
('noti_tkn_f7f6bfe1b3d3784b5123d78a31d49a85', 'dbn47xtFNJA:APA91bHLmw9wG9CjGi1JMytmh49Eq3blTBpnaC8sCNiK9-6tYYYcesvZgTzbQ0Txss3G5-Cyg6ogXB4hkBd6JwKenLNh5elyC9Er_Z5oNZ3BoVV1pnePJ6Vzg0zlzfOP0WOzJsRj4iVn', NULL, 'usr4cabff2d1f8a3cb50fb685a967d0e596', '2019-10-17 09:12:34'),
('noti_tkn_fa563c62c814cc9f41bc71f3bd91aa93', 'fPYFFymitDQ:APA91bHWMiajrAtb-JeGYW8AFI8yjGBM5Y1yW4QP-8J3cJ0ccAWa-y2Z8-gLF8z47VDejulTZuj3DVq7CQnEqEdhBlA6SnpOHcFht-zgvpM_j9czuuE1CMNdrs7gjwtEoRAWgJlmHNjH', NULL, 'c4ca4238a0b923820dcc509a6f75849b', '2019-11-04 09:40:30');

-- --------------------------------------------------------

--
-- Table structure for table `bs_push_notification_users`
--

CREATE TABLE `bs_push_notification_users` (
  `id` varchar(255) NOT NULL,
  `noti_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `device_token` text NOT NULL,
  `added_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_push_notification_users`
--

INSERT INTO `bs_push_notification_users` (`id`, `noti_id`, `user_id`, `device_token`, `added_date`) VALUES
('noti_red_4159dda9b0b05147f9c5db6108e470b0', 'notic9a3ad3dca6672cc5075efbc9ca39ccc', 'c4ca4238a0b923820dcc509a6f75849b', '', '0000-00-00 00:00:00'),
('noti_red_48db15f1b9e0f0a7f246cd5e56d7c6ba', 'notic9a3ad3dca6672cc5075efbc9ca39ccc', 'c4ca4238a0b923820dcc509a6f75849b', 'dixveG5G-FI:APA91bF2R3Iei15TuODpIi1ZWeXDzly3uQ2txlFuk2Z4RXpDzRIlf9IbUQtvrzb1hMe_FjadyA8dxm2oj4JNpVV6vqtTYZ8bh7FxxnMyp8fmJI3A9TtpOUnJDrxA67i0cD2RIENmRp_F', '0000-00-00 00:00:00'),
('noti_red_52eade3900b8a07bf879e4605c6a9d40', 'noticb8657dc0b0158ca9168ae6bc98254c0', 'c4ca4238a0b923820dcc509a6f75849b', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', '0000-00-00 00:00:00'),
('noti_red_562fc323489c56134b059b7d41a26deb', 'notia12d6755b0fe70765f8834a10cd51e6e', '', 'e6olJaGbB1A:APA91bGjoh7u_j5sTA88lNblApzEExvuLIsMg6j-9LzeOpEZgzb1dzo_9el7lT1xCC0e6fLY-NoZTQvu_FwCeNlqfTn2xBWTyME5vjTgmRnUJoOXG5WrGoivt9iJN7unc1pG92nfJRJM', '0000-00-00 00:00:00'),
('noti_red_59fc1f23243252ea2644862dd67c4fc7', 'notic9a3ad3dca6672cc5075efbc9ca39ccc', 'c4ca4238a0b923820dcc509a6f75849b', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', '0000-00-00 00:00:00'),
('noti_red_6ca7266fda75c4304a35a86f2b689c8a', 'noti5133d4cca0f78d28528fe89da2041168', 'usr9bbff91915b2c1a9566b99fbe54c75a4', 'cuS_WG7Ba54:APA91bFygI-Rz7U_CjTWcsJcaWrdl8j3EEX2Rp3wCY2mtgobUhv97X5MDwQhEeVFLDBDPVGDMsznAXug9Sk3UM9g61DWw5a29YM2jGrM9ACJd5ohOQRhMMSX8fDbuog4is2S95JsWSRA', '0000-00-00 00:00:00'),
('noti_red_a2d4ea9556c1367dcb469d27a2c2b55e', 'noti97225230a7badf761c5d2e4542b616cb', 'c4ca4238a0b923820dcc509a6f75849b', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', '0000-00-00 00:00:00'),
('noti_red_a90988c23284e6c5a0d7ac4db1ed725e', 'notie3a73fc9d0d1cb34b1fc5fbb973d10cc', 'c4ca4238a0b923820dcc509a6f75849b', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', '0000-00-00 00:00:00'),
('noti_red_b1fd30d7f966b19174b18d7a97d6a120', 'notia12d6755b0fe70765f8834a10cd51e6e', 'c4ca4238a0b923820dcc509a6f75849b', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', '0000-00-00 00:00:00'),
('noti_red_d7ac9a30ee70d2bc4a9e97700fd5d55b', 'notia4f5cc0da551a0d999c0ee13504b072b', 'c4ca4238a0b923820dcc509a6f75849b', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', '0000-00-00 00:00:00'),
('noti_red_dcaeb0b76b5d15053c05a753bd56d037', 'noti5133d4cca0f78d28528fe89da2041168', 'c4ca4238a0b923820dcc509a6f75849b', 'cn2-7S99yTM:APA91bHkS-Y-L2VOuDdzobx1f2eDBovZouhN63nrNHhwyB8A2o8qjeY1oQKhrKqOSyKXdl2ZskzCbYQ8Y8p3mVqdFxo9EoIV5OY1eQT-IsEuWdaEyXKq-cOukqq0HNEuo3FvUrEVcK9G', '0000-00-00 00:00:00'),
('noti_red_df2d64eb15ba4d48267c48d1e3adbdd1', 'noti97225230a7badf761c5d2e4542b616cb', 'usr9bbff91915b2c1a9566b99fbe54c75a4', 'cuS_WG7Ba54:APA91bFygI-Rz7U_CjTWcsJcaWrdl8j3EEX2Rp3wCY2mtgobUhv97X5MDwQhEeVFLDBDPVGDMsznAXug9Sk3UM9g61DWw5a29YM2jGrM9ACJd5ohOQRhMMSX8fDbuog4is2S95JsWSRA', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `bs_ratings`
--

CREATE TABLE `bs_ratings` (
  `id` varchar(255) NOT NULL,
  `from_user_id` varchar(255) NOT NULL,
  `to_user_id` varchar(255) NOT NULL,
  `rating` float NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_ratings`
--

INSERT INTO `bs_ratings` (`id`, `from_user_id`, `to_user_id`, `rating`, `title`, `description`, `added_date`) VALUES
('rat225499a1736b052a1adad9d3a312b3af', 'c4ca4238a0b923820dcc509a6f75849b', 'usr4edb805ca791dc7677ba4a785873debd', 5, 'satisfy', 'awesome', '2019-10-02 11:58:43'),
('ratac4dd6f76c6a3ff20eed136e90fb88b2', 'c4ca4238a0b923820dcc509a6f75849b', 'usr84264292f0b3360143f6ea8776b77f20', 5, 'Super Nice', 'loved the service.', '2019-09-09 10:28:21'),
('ratda50efb0ffd106acda0c17b9ae3fc565', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'usr4edb805ca791dc7677ba4a785873debd', 2, 'BBB', 'BBB', '2019-08-15 10:21:48'),
('rate55db7eef61ab2184884c1be9614db34', 'usre42ae5690b8a7e0140a762d458bc803e', 'usre71177a5c87155245fc6d7ba39043963', 3, 'nice', 'nice', '2019-09-02 07:28:47'),
('rateb195f07949f11d18b149fcaca7c7778', 'usr84264292f0b3360143f6ea8776b77f20', 'usr1ffb51feddb5c60deeaacb5c644257c7', 2, 'GGG', 'GGG', '2019-08-28 08:43:58'),
('ratf1df7940708aa1603e9f0998b83b7be6', 'usre71177a5c87155245fc6d7ba39043963', 'usre42ae5690b8a7e0140a762d458bc803e', 4, 'nice', 'nice', '2019-09-03 03:48:17'),
('ratfc38f4e6d20e45de5edbb4e3c114a797', 'usr4edb805ca791dc7677ba4a785873debd', 'usr1ffb51feddb5c60deeaacb5c644257c7', 4, 'DDD', 'DDD', '2019-08-15 10:24:15');

-- --------------------------------------------------------

--
-- Table structure for table `bs_status`
--

CREATE TABLE `bs_status` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_status`
--

INSERT INTO `bs_status` (`id`, `title`) VALUES
('0', 'Unpublished'),
('1', 'Published'),
('2', 'Pending'),
('3', 'Rejected');

-- --------------------------------------------------------

--
-- Table structure for table `bs_touches`
--

CREATE TABLE `bs_touches` (
  `touch_id` varchar(255) NOT NULL,
  `item_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_touches`
--

INSERT INTO `bs_touches` (`touch_id`, `item_id`, `user_id`, `added_date`) VALUES
('tou000bee2dba17b37e6276e11b92fbf36c', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:47:43'),
('tou00104ea98cd8f0418d76f7ee79f8d221', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 16:21:41'),
('tou0044e94f8cee28475309ac5329d73d0e', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 03:34:37'),
('tou0066a8f6d8cd8a7a72fa07f9e822bba0', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-08 07:20:08'),
('tou006ac14592783725b6262475bb60f3c2', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:33:12'),
('tou00903b8937bc5fc75038ab04de6fc185', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:11:43'),
('tou009e676ea2ab272ef7aa141ea358965b', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-13 11:16:17'),
('tou00ac63453743b2a4db708d0b50fedd2a', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:12:17'),
('tou00bc4b80706289bd2ba14e0a85795adf', 'itm_8277fd5c824ab26ec25f902c7026a40c', 'usrbbb81d64f7d56bdc2d7c0dff91bd2346', '2019-10-07 07:03:43'),
('tou00bfe7fba1bf7546684e9607986e0571', 'itm_a65be83fb825406c14760f8848ff7bfa', 'nologinuser', '2019-06-24 04:34:12'),
('tou00e9fff1863297fb9650284390dcbba0', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 11:38:30'),
('tou0139c55d81dbd5899e55e789a214d163', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:38:23'),
('tou015dca525ded460fb68eb2698d8463d5', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-01 11:51:16'),
('tou017398d880f1bca83ed9e44733dd61f0', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:26:19'),
('tou01749c6a116e7e6b9d870e5d4ba7e147', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 09:13:35'),
('tou01a04aef09139bd74350388860a0f40b', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 10:56:22'),
('tou01b2a8852348c53b6097f5e00b3a3739', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:30:24'),
('tou01d8791fa78bfde75bd3894004a4d624', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-13 05:47:06'),
('tou01efd4383c15668cee92fb431bc82ba7', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 10:30:17'),
('tou0216d6ca1f78e0b3c2525d8e28590b5e', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrf31597aca589e0f1b43061cec1f399b2', '2019-10-02 07:02:51'),
('tou0280efa49f7bd8ada49c7f12cb42de9a', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usr8ad862ee8b0eb6495d389cfe6f35655b', '2019-09-14 06:14:10'),
('tou028c42de94a1c336f9ff1a33f09bee2c', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 07:38:51'),
('tou02a977d74d929d51e0f56177576d72bc', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-14 09:11:14'),
('tou02b2ce55244259e140f8253fa522bb16', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 07:42:48'),
('tou02dd8a160ab121b59cf4385de0da8450', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:41:08'),
('tou0318d1ee6f0578e494a7eba01a9d6315', 'itm_7359de3d150f84bd9e06d9e6054ba151', 'usr1db69cc9b7c316666af1bb9d623e031a', '2019-10-07 07:49:08'),
('tou0354d5b22199992d1f0ee2ad45011270', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-10 20:50:04'),
('tou03577c623438a10c8ed1fa2f623994c8', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-06 04:59:44'),
('tou0367cf12da76563d6d94834058d2ae03', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-02 11:27:55'),
('tou037e13b37791a0cd9a4da138d26c802c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr812a65884b5bff6775cba514ce8288ed', '2019-10-02 08:05:02'),
('tou03a44f273fe0bf9dda58e8ebfd5e52ad', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:39:28'),
('tou03b46d53808efc1abc9bfb8701794b9b', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:32:45'),
('tou03c21470880c4ca33fbc80409e660abf', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:27'),
('tou0409fefa0fe740f3f53681a88e5d6f42', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:11:00'),
('tou04200a92ef9b55af2b0ba47064734222', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:23:42'),
('tou04625214242bf73c8b13e7aed06aa540', 'itm_0500dcce80c7d70b79453a8ff4096428', 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 08:03:25'),
('tou0468494207c4c7e193e14f6d4c78320b', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:48:56'),
('tou04abf4abd96cb3a265d7275f0edae55a', 'itm_ea7bf09df31b0e839795b0d44e7d963d', 'nologinuser', '2019-07-23 06:36:01'),
('tou04cc3e097a6ed97d612c2d40e404339a', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:56:06'),
('tou04cdd26a7b08a53e11d9ea5974dc856b', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:55:39'),
('tou0502cd6ee62d09dd470e2f2708656e6b', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-08-29 18:44:28'),
('tou050d53fc6f665acc6101b4235676642f', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:12:20'),
('tou051e9351bae4b85065ab8629d1c1e739', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'nologinuser', '2019-09-25 05:03:18'),
('tou0524b0a9ba446e6d2fa1b7978f783841', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 08:18:40'),
('tou052a76522ec9295ad6db56935f13f52c', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 18:21:59'),
('tou054cdac6b02ffca5bdaf89f9d6f8a548', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:02:53'),
('tou05a687372c41567965866453c85c3620', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 18:58:19'),
('tou05b57bdc1467fa571286d8ba47110071', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:39:25'),
('tou05c4a3930cc27e4525bb08898f0fca16', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-06 11:30:31'),
('tou05c7a6f3183499d2c0d68a6b55bf7af6', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:29:45'),
('tou06050aa6388c9e99c4889ed1670222f5', 'itm_aec300faa4929e3342679e55e63c24bc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 12:41:13'),
('tou062d43b8487492c654a6517d2ae4a16b', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:30'),
('tou0660e1a1fca34ff03155e1c1fed16a9a', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:35:53'),
('tou06a0a4914b4dbbf3326bb6281c140b6e', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:15:11'),
('tou06ad873562306468ca4b32223d5282fc', 'itm_40c6367fee11bc95a223027f129e2bee', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:30:03'),
('tou06adda2bee9b972eac4b18ec3f66daaf', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 11:55:17'),
('tou06c555683720db78f6c2a508fce5d417', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:24:04'),
('tou06fbc0631fcc0f51f9fcc112768e7b02', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-04 22:48:42'),
('tou0711c2861ea5888e8400cebe1f9d327a', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'nologinuser', '2019-08-29 18:44:32'),
('tou07316ef05a82355216e7351a02b2c532', 'itm_b4f9fddb9b6f3a6bb0f8baaef7da4967', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 21:22:06'),
('tou0770f927ae0d3af47c6b29f228516846', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:12:24'),
('tou07722710adf8388148593abe9a69d79f', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 11:29:47'),
('tou0796a8e030c5c88a25aa535b758b5c20', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:37:03'),
('tou07a2dc81503a84fb8b01924c658ae1e7', 'itm_3bc2dd9f6f7dbfcefb5238ae4a6ddf2c', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:27:08'),
('tou080cd1128d405b13c8285048135f454b', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:17:41'),
('tou0844a56ba2d7c06d1c79590bdb8eff9c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 03:52:42'),
('tou08512b1c378949432e7004a979503fb2', 'itm_709970eef73e104ca5d4114b2485355d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:39:31'),
('tou087da779903441edee2f8e89c868e390', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 06:07:24'),
('tou08931aef2a00014ad47d1efe97fdffa9', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:12:34'),
('tou08be1d01363c78c72fbe24da12dac088', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:38:49'),
('tou08c9977414947b26b020dbe801dd5b56', 'itm_709970eef73e104ca5d4114b2485355d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-05 15:16:10'),
('tou08ca623ae4bda52fe70d010025cfd935', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:40'),
('tou08fb6976784a6db53302ce91388da5c4', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:46:48'),
('tou09545538ac5960df024fe0e26f65415a', 'itm_54709837e70ae2f179b49a739ded54d7', 'usr3ef9bac5ec67dac879f099d0faf16d84', '2019-09-06 10:48:49'),
('tou095790f72e48db8b07b35133937d443d', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:27:57'),
('tou0962cee266a8a98b902a279cdbabed7a', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:16:20'),
('tou098cc7eba549743d34e390900a5446a4', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:25:43'),
('tou0991e3b04fcd5d08b2a04f21629d6dcc', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-30 06:13:32'),
('tou0993ddca7c7528b16b086d6884df04ce', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 11:12:59'),
('tou0998a51def07228641eb23a9ab3d04d5', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 03:52:36'),
('tou09b5d51142b637018ad47e0d218c00cd', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:36:59'),
('tou09dc69b9b608ec35565c81ec3a1875d5', 'itm_d226e2115d5632e9aaaac89bd880737d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 13:15:22'),
('tou0a5802c9aa24d37de8b5c086e6e78efb', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-10 20:53:05'),
('tou0a6715ff509fae8bbdb795658cd7ceb7', 'itm_b5372f02c59649e04d0639862f151119', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 08:12:40'),
('tou0a7ab50ebd0d74dc998657ace91a83b3', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:51:08'),
('tou0aa6a16cb803f1a36a2b9e0eeb3f7089', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 11:21:01'),
('tou0abf4a3368a75cfb429a32e98182d2d0', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:09:04'),
('tou0ac2033c94f1c9d51c6c73a11e6c1374', 'itm_bfd764c6d3b307679d64c24525ca85c8', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 04:23:00'),
('tou0ad1d1b9d2c89a6549a60642fcac9d94', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:44:28'),
('tou0af46aa98730648505eedd0cc4fe8f8d', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:27:40'),
('tou0b0bb73ab4ff1f0962cfd53d7bc74374', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:33:09'),
('tou0b7411d088a31af69075d150e0832dcb', 'itm_54709837e70ae2f179b49a739ded54d7', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:21:50'),
('tou0b828c0bd2742dcab77dec11bd5ed788', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-23 19:56:40'),
('tou0b970d8703162ab24567231ba79cfa28', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:27:48'),
('tou0ba2cab414d3dbf2c339c6020415423d', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 23:26:46'),
('tou0ba66b6659df8ac637dd66f19434d65d', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usrb964408bdc58811464b1b6c5751c8d97', '2019-09-12 17:54:44'),
('tou0bf42695870e2a24ce411a49562aa169', 'itm_02db30c4e6a968ac1955cba086d95b17', 'nologinuser', '2019-09-11 05:27:40'),
('tou0c18e46c5ce76daa6f47d7e9dc811dc5', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:59:53'),
('tou0c1ab825d36d6947b4ffb6169fd2a227', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:36:10'),
('tou0c225eb9bad7d02e2a51058ac4afcf02', 'itm_ba0837310e18f29408f09fca748271ef', 'nologinuser', '2019-09-12 16:10:57'),
('tou0c54af5a120134b59a4266d140622807', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:05:54'),
('tou0c5edb8a5f4cc0cade4d8f1c909b7c15', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:02:47'),
('tou0c6c7eca4132940a2f76793509dfb580', 'itm_b2f06f3cf59f3b1be75fef276f389ea0', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:01:34'),
('tou0c70270ab3faf836c5330d88313331f4', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-04 05:08:30'),
('tou0c87262030d8872c2721cbb307367d28', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:42'),
('tou0ca1b26f6f8e548105eb8fa60e700145', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-04 05:08:28'),
('tou0ca1ddc2e6442d793cf2bc4d33d649c8', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-08-30 05:34:18'),
('tou0cc0d5d2f150b8104aee72d3ff7c233c', 'itm_9ef57934867903960a9849bb7af133e0', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-15 16:22:21'),
('tou0cdbfc913a8c4b10e470af89086459ca', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr3f57bf66eac7eebcf7cbecf84651d96c', '2019-07-02 08:56:48'),
('tou0d01cd73d414771277d9796ed30407c3', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-14 04:03:28'),
('tou0d1f1a82b8c20dcc4e38fcc9ae746747', 'itm_e9584f6420da26e2378adffc455f7431', 'nologinuser', '2019-10-14 20:36:25'),
('tou0d271074d5f6e0ba0f9714633c169a84', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:34:37'),
('tou0d682d1d97c24cc97bc2a2e8ec6a0101', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:40:45'),
('tou0d6a763166d7b00a411005321dc67c5e', 'itm_02db30c4e6a968ac1955cba086d95b17', 'nologinuser', '2019-09-09 14:27:52'),
('tou0d79ac3108357da98d9b0366eb5b126d', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'nologinuser', '2019-08-29 18:43:49'),
('tou0d98f1034804bc07a4f5ed0cafe9eb50', 'itm_40c6367fee11bc95a223027f129e2bee', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-29 18:44:18'),
('tou0dcaa1bf32c65231e254edba7e25b9c3', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 12:18:48'),
('tou0ddf149afa34c5949991065a310a1c41', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-12 01:04:30'),
('tou0e1414bc3f785f980f996b46cd5da2e9', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:07:44'),
('tou0e19a2f031e02b05ab1bd8a38aa57262', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:18:30'),
('tou0e2c53d7421b5d5e3f6d84737c8fbc4e', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'nologinuser', '2019-10-01 10:34:31'),
('tou0e7591ef7a589fd1e3af8129f949fc6f', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:49:31'),
('tou0e9fdcbb87c2f7e89342dcfd07f91196', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:22:00'),
('tou0f607290f74fc5128e1481284e0d64d6', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-20 10:44:40'),
('tou0f7f1a591c1283c3ae54109fbca52643', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:58:47'),
('tou0f87e3e228933e0f52ea881d0b5f821c', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:59'),
('tou0f9d512fc57fd5888c8b360be6f3707f', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:49:51'),
('tou0fb5b96b0c579617e43793c5067d898b', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:45:09'),
('tou102ac872001f245b964c13dd99f52096', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-15 06:34:46'),
('tou1035cf626edbba1ecbc2e3a4d5ceb7cc', 'itm_528c99e1abd619f814f246f0daf3f734', 'usr9c7a5d7887d53caba5ba4d94bf1b45d9', '2019-09-08 01:55:28'),
('tou105866dbdf23b3b923b263f2117ea180', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:01:49'),
('tou10c6ae87b9a8d99ae1824c544c455b37', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 19:37:47'),
('tou10dd7bf18ce4f74c35c992655711ee02', 'itm_33421d314109f37e8cff807cd4149cee', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 04:19:47'),
('tou1128b2b4419d4930d87b2d729f6e6815', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:31:10'),
('tou1148d9a2714e20a13ac730a30ecfa42a', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-07-01 07:45:03'),
('tou114f61f74748bbb16b98b9c3f7f1ae5b', 'itm_92994f957013924861ccf81e6a8ea4dc', 'nologinuser', '2019-09-25 23:26:31'),
('tou1151baf8656825a5c2349836336ad918', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-11 07:30:47'),
('tou115df76cd4821b377fd263588f8d699f', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-14 04:08:53'),
('tou1180fa91c1e51405a4a3a26bc3c00056', 'itm_9ef57934867903960a9849bb7af133e0', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-09 13:53:28'),
('tou11e877d001b6896b245fdb2f58080908', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:53:03'),
('tou11ec25ec50d1a1610f188409ecb4eb61', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:22:55'),
('tou11fe19bfe5195c691d070b6d36ca6bc4', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:31:35'),
('tou120728aca10a12ce4ee7dc92b5772a91', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 18:46:58'),
('tou1207ba1d46b7a64a101452d3d444862e', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-27 05:14:28'),
('tou120c3b4cc7846557bec704fa602c1a57', 'itm_b5372f02c59649e04d0639862f151119', 'nologinuser', '2019-06-24 04:34:15'),
('tou1222ad25ab8e06431db0dd9d61f5c968', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:23:06'),
('tou12352de5cd2f0ba9b8156c81b93ca577', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:12:09'),
('tou1238dacaf5967336d4654076def105f2', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 10:45:38'),
('tou1253974315e1bf8fdc65bcca31d56ea5', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 04:28:40'),
('tou1255446a69cbb0d0139b9ee96504977a', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:38:33'),
('tou12788c7b309f5b65db2214437f168779', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-08-30 06:13:13'),
('tou1285de59098a0b4e092c92164497b9bf', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:08:45'),
('tou129d298a69b3779f6ee14a27704832d0', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:12:46'),
('tou12bebfccb52dd97cc178dce68f9296f3', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:30:25'),
('tou12cca5cc7ca2a5958aa041abaa638cc2', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 19:19:48'),
('tou12d8b40eacda28ebc6bceaad971c4889', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:20:35'),
('tou12dfbd4b6f72f1cd04513a54f72055a3', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:37:27'),
('tou1304607e33c32f08f38c3535f800a3bd', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:28:55'),
('tou1306d17c162aadd0b2c7dee9ecb2cdc0', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 07:24:46'),
('tou130a6fcfb8a35dff6f4bedb094a017ec', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:19:29'),
('tou131d0c602f63f3649e9f4318b71be078', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:48:38'),
('tou137467ac96d20cb1540307c0e8091ab4', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-08-29 18:44:09'),
('tou1383edbbb3292441e672332f2fd08eeb', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-10-03 03:26:44'),
('tou138b34830e9c244d1a8b0d19f4bbfc6b', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-08 14:03:44'),
('tou13a0fa02a0a5bca129b4a7c2151c3d23', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:34:12'),
('tou13aebc64038f04acfc9b5211b8915163', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:13:47'),
('tou13afd7805512bb077b9fdda90358ef16', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:44:33'),
('tou13e78df9ae21ea201ed46208914a8c02', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'nologinuser', '2019-06-28 11:21:42'),
('tou13eec499b1521eca7d7cd943c3fee281', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:34:04'),
('tou13f97acb851c87d7b0d9de2a21e44d0a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:06:36'),
('tou13fedbee8b9bf1ac47d9047bf60b5999', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:52:57'),
('tou140fd899bd2643a57f72717fec6d0a1f', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-27 11:11:17'),
('tou14142f4bce0fa47eece0e112199ea2d1', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:10:30'),
('tou1421338d926a194b8460566c95203127', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:14:02'),
('tou143588f7f06dcfcb1e1aaa2d4a65acc9', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 10:55:05'),
('tou1463a04e738cd1c011f3f17cd16851db', 'itm_19d8a632ca279d2a4739913172cb71dd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-08 07:56:01'),
('tou146cd15159d580cd67b5ee5458bdd3c4', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 05:21:53'),
('tou14786d60a021fbec591e6cc0ca20afb0', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:16:44'),
('tou1484196f890e7828782a4ebfa7ebbb18', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:18:20'),
('tou148fd68742fd09da58ac6645bf643ce8', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'nologinuser', '2019-09-20 10:43:39'),
('tou14e377d645a56b7640f39e1f24819da1', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:43:09'),
('tou14ef305e638a7f84eba90f00eddf3f79', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-06-29 15:33:32'),
('tou151cb0212cfcc3587761c2e364c0f875', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:34:31'),
('tou15225436cd9e27f70e1d7792f0fbb15e', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 03:43:05'),
('tou15417b292250d9485587423bf437b44e', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'nologinuser', '2019-09-12 16:09:15'),
('tou1556a86768d7bf7090a5bf5582e0ea6d', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 13:20:12'),
('tou158c87dab3c2a7def0011bf7596d649a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:24:05'),
('tou158cfade5f5a37847285b999330ee6a9', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-05 16:20:45'),
('tou15a955c7259ee78ddf298efd80ac56bc', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-12 17:05:54'),
('tou15bdd22b559100b0343d540c1de09025', 'itm_79324075063082372ebcb5b759b2bf7a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 12:15:59'),
('tou15c9932723e2b0af86b2b80bc8bb8bc9', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 11:22:46'),
('tou15ce468aac6b43ce0abd3cb506fa7a4e', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:25:15'),
('tou15e4e4adb07dbdd8c278190d93cda8bc', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 06:59:30'),
('tou15e9373bd3b7d7aca3fb3b16b4e34874', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr812a65884b5bff6775cba514ce8288ed', '2019-10-02 08:04:55'),
('tou168c6b1aa880abab912d674b45b36af0', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 16:53:20'),
('tou169fa4ec60cfc809b627611e7bf2c0d4', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 05:17:01'),
('tou16d03b856a87f594fbe1cacc58d9160a', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:58:35'),
('tou16d4dedc6321dd3249293218d92507f1', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:33:03'),
('tou16d576a27f6efc1e8c28c90d188ccf54', 'itm_81e1b4c39551a5765743662e1f5f079b', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:43:48'),
('tou16e3582fe5b7f14927d62716312fce37', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-09-02 04:46:56'),
('tou17799d515cc85549465d474818a52826', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:33:34'),
('tou17820242ac14a8d288b10c43608cc559', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-11 20:57:47'),
('tou178676a94b07eb245e76856e904659a5', 'itm_3574960a0f206a81307cfea2804acbed', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-14 13:55:02'),
('tou1790da731002fb16436f638f78cf4183', 'itm_ba0837310e18f29408f09fca748271ef', 'nologinuser', '2019-09-07 18:18:29'),
('tou179992690abb109f82f86390aefbb695', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 08:11:25'),
('tou179b9c482f5f218a1aab92f7e6e68eb0', 'itm_2bb3fb13e1040cae112485657e31f868', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:01:00'),
('tou17b1ab075a5a65b274ddd8df6aacd888', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-04 05:08:37'),
('tou17ce85ceca971b642c349327c18ed8f7', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:43:07'),
('tou17d7f78ee0808c6f8ced8bfeb15ca7bf', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 08:08:14'),
('tou184be8395c2ae277bcfb80029fff48b1', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-20 06:14:28'),
('tou188032d41555f17cbde4a7895449e8f4', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'usr706a71c3e5bb7b0f5297fc0ec706a61f', '2019-10-04 08:55:03'),
('tou18de18f02a4750c299852729c44ce6b3', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:41:14'),
('tou18ee7dab9eb829e81cf0847dba853e27', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:31:10'),
('tou192b4dc8edc203707eb9772c6a6cbe33', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 05:24:24'),
('tou195e59bbf548d70efcd401022d952073', 'itm_3574960a0f206a81307cfea2804acbed', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:43:54'),
('tou1961f2473174dd64bcefab3b2f8b0f6d', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 04:01:07'),
('tou197176ffafacbf6dd248073765679719', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usrf75bd4e3f445c1f676b725b971d77084', '2019-09-09 15:52:46'),
('tou1978b84e1e2ec855a7e54862b923edc5', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:12:52'),
('tou19ae3f93f5bfd641c706d0b2772e4be3', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-08-30 05:39:56'),
('tou19b0d90721d3a9d486e526f9e51a7f70', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:46:52'),
('tou19c78594b62e4069c74d104281d0ce79', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'nologinuser', '2019-10-04 10:12:26'),
('tou19ca7f414bbbf1c9b59c875090dbcf51', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 10:19:10'),
('tou19e55f3741a77ccfbfeb5e72d2f1803b', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 05:16:38'),
('tou19e91c4fc78513f0efce773be7bde5dd', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:56'),
('tou1a005bbe0d7cc1e8dedc30387c16fdb8', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 10:02:10'),
('tou1a28d797d3797e56d82957c122064af3', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:54:04'),
('tou1a31f09766c7ab8962dc623bdd2057d8', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:50:55'),
('tou1a3d7170dd402503732a6ad5c6ee0ed6', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:02:20'),
('tou1a6e2b0408de2dea23e31e820a206d85', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:39:39'),
('tou1a8014aa00c58024fb50b10c76939eaa', 'itm_0500dcce80c7d70b79453a8ff4096428', 'usr9f42abfc8638a5e733125a12f37bbf2a', '2019-10-07 08:05:36'),
('tou1abd1a452a90475725095294d0289919', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:28:23'),
('tou1ac4862d251796adda5cdca8f5fae32d', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'nologinuser', '2019-09-12 04:28:34'),
('tou1aefa9b85dc457db1a729d80b53c5b00', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'nologinuser', '2019-07-01 03:30:13'),
('tou1af7e8624cd5ac82652c057e1260672f', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:01:37'),
('tou1b078e8713246b630a37a50f752bb2de', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:03:57'),
('tou1b5163f1eae7d5bfe9d897f026e979b7', 'itm_79324075063082372ebcb5b759b2bf7a', 'nologinuser', '2019-10-14 13:53:27'),
('tou1b666b13e38b8920a10a425f5d8173b6', 'itm_19d8a632ca279d2a4739913172cb71dd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-07 03:39:32'),
('tou1b83e4c92faea1ff06983d2f8a9a6e24', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:55:39'),
('tou1b97839095eda60df5f9699de5409d30', 'itm_f1864130c55178f6c61cd76350426c40', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:08:56'),
('tou1ba7e6d6aa2ed3054f8c91b8ddd490f2', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr1c30bc352b4dd71bb0469d1b02a65da3', '2019-09-19 10:48:04'),
('tou1bd54c8cc71d56f5b2774fd2830defc9', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-29 10:46:18'),
('tou1be295ec9166852753e3b91b75a92392', 'itm_b5372f02c59649e04d0639862f151119', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:18:12'),
('tou1c087395bafde00c8c58480eb11ac2f7', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:12:19'),
('tou1c526118f0180129f8b77c8be767d5a6', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:24:26'),
('tou1c582f282aa22af5c1415442e9e95e59', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:22:56'),
('tou1c98b4a86f073041051de6162b0abbf9', 'itm_7da4b726eeb3a492413789219c83c6b2', 'nologinuser', '2019-07-02 07:57:52'),
('tou1d0d42bec67e1076431cca219b45aae0', 'itm_b5372f02c59649e04d0639862f151119', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:24:01'),
('tou1d18218769dd008d53a4241a1ba960bf', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 11:40:13'),
('tou1d33b7145270cd40aa280ab164fcb8d2', 'itm_528c99e1abd619f814f246f0daf3f734', 'usr9c7a5d7887d53caba5ba4d94bf1b45d9', '2019-09-08 01:59:45'),
('tou1d3dbf784989a67f4dc617d348e1090f', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 15:27:00'),
('tou1d5c3b02d10b034ed04183d007ae789e', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 09:03:37'),
('tou1d66a2e2af689393243fc4e0bdbe43fd', 'itm_709970eef73e104ca5d4114b2485355d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 22:46:06'),
('tou1d8bcab755cf27d31986770760595bf9', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:01'),
('tou1dad5edb979cb29791c7b06bccdcf19d', 'itm_c80942f3ecbc906985d16f32f4355072', 'usr8ad862ee8b0eb6495d389cfe6f35655b', '2019-09-14 08:27:53'),
('tou1dc74e8d7ede35063556804b4f233485', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr802e5a133a8caaf0c428fa88c6bc9eb6', '2019-09-11 19:02:20'),
('tou1de75cde4add3a28bf5ef9766922b348', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr8ad862ee8b0eb6495d389cfe6f35655b', '2019-09-17 08:49:50'),
('tou1df66e9f057883e29f4f8bb011025171', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 00:24:54'),
('tou1e39d9948a1189032e9e0d773a952c98', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'nologinuser', '2019-10-04 10:12:21'),
('tou1e3f01a371197fa9b28164479d0db865', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:35:07'),
('tou1e4ea9aa4c3e6daf6eaf2a3c1776008b', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:38:51'),
('tou1e577ea1fc5fb8a1b7c298c884c69705', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:45:12'),
('tou1e86ed7c447774fc52e4ad72ad45f1ab', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:40:41'),
('tou1e97493d91f26c2b8f4545f2d3b4f6e0', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:25:11'),
('tou1eab3df19981e520fcd96ce22d560095', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:13:13'),
('tou1eb78d480480788556bc3acb39bd1de5', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:01:44'),
('tou1ec0acce9b80ff1ddf9f5433015fa4ec', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 19:20:07'),
('tou1edc79e1f3060b9f834cfcfa2b1f4123', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-10 22:15:32'),
('tou1ee4784fd8291603a59cf81739b7edd6', 'itm_2bb3fb13e1040cae112485657e31f868', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 08:21:17'),
('tou1ee9c4a7060d0da874fad67d26d5f7db', 'itm_5058fb44b1f3a2efd9cbd0925ab67785', 'usrc58fe4f5fea11c19ee38cc1de75949ea', '2019-10-07 07:27:01'),
('tou1eebfcf663125002c62fe8ee9a138e85', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr8ad862ee8b0eb6495d389cfe6f35655b', '2019-09-14 11:22:02'),
('tou1eed7a205ded58adb6e0592071d99263', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:30:45'),
('tou1ef0640d5f05788debb0e3d43c414ba9', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:40:52'),
('tou1ef70699a0ebf627246f9ff3d3942c73', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 08:07:14'),
('tou1ef93b0bcc81b118ac1fb8d515fe198f', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:50:47'),
('tou1f0af54911170701865fafa6d925a998', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 09:59:30'),
('tou1f102183bc15497756e2e70c0bf6eca1', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:22'),
('tou1f16e0bc6e12323b57685ccb4b55fa2e', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 07:42:52'),
('tou1f1ca5252881c6a72d8ad34be161a48d', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'nologinuser', '2019-10-04 10:17:01'),
('tou1f23db0588836488b955eeef848b3aa9', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 08:04:54'),
('tou1f2b63c85febebf3c65f604ee908459d', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:06'),
('tou1f2f344d0f6b41c88bf97c80f6768256', 'itm_3a197e7a07668e4d8580e5b129957107', 'nologinuser', '2019-09-04 13:15:02'),
('tou1f3429ead1f9824f0446579f45ee71de', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:08:13'),
('tou1f3cf0d1e5ad7cac55453935b60fd9ed', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:37:52'),
('tou1f4029ffb4e9e424f914696fd5d8f2ac', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 07:02:29'),
('tou1f56db363114bbca36419a3d8683c935', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:12:48'),
('tou1f668c8a334f1b6442f3c136eedf79fd', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:14:51'),
('tou1f6f4d6fb12eca7829579b21e6dffa41', 'itm_40c6367fee11bc95a223027f129e2bee', 'nologinuser', '2019-10-11 20:55:01'),
('tou1f83e7e96815c7d0e0123638972f2564', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr9c8be3fbd3f169f5015105a9c44f7d30', '2019-10-02 08:07:11'),
('tou1f86ae78309a66bc8dac2288a6261cad', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:03'),
('tou1fbfb3a8bc13a594031e77e8d0148d85', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:39:31'),
('tou1fccd224fbad3f4efc73ead6a223ce05', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 10:38:56'),
('tou1fce7b87c32810d5ab6dc954c43299ff', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:31:10'),
('tou1fd7eff3c73106e6385068449329b532', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:33:44'),
('tou2002a21468ce7ebf1470d378e74c8cbd', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-01 11:51:30'),
('tou2022c048eb77c2114b00694fcabd861f', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:30:00'),
('tou20539985f217752d430b76443feec9dd', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-08-29 09:57:46'),
('tou20789237fa43f4f220a99eb0c747c0cb', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:37:14'),
('tou207ac4c4ea47b39c03d154a2b0a8eea1', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:19:26'),
('tou20b409ac99282f307a20b097e33999de', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-30 23:18:30'),
('tou20d5dd685a81948b6ef8b386111e6036', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:48:28'),
('tou20e88516dc4ec55b7a6a7fb15d8864b3', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:01:09'),
('tou20fc50856ac36dcc94ad8bf495ac7647', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:31:52'),
('tou21089fc436c2bd5cdf1361ee1d64b37d', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-09-27 22:44:58'),
('tou210aa2051cf08ccdfa01aee5ff01a322', 'itm_4a14b1820f96a2d94a1d2d4a537dd1d9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 07:38:08'),
('tou211dcc14cc6bb434b202a89b9cb1b83d', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:53'),
('tou2132c147e16ee587685263263f8c9078', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:25:01'),
('tou213e201992879cba61516f25443f7821', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-09 20:33:36'),
('tou214882f12b425b5fa6e192607fd2fa73', 'itm_a65be83fb825406c14760f8848ff7bfa', 'nologinuser', '2019-06-28 11:09:21'),
('tou215417446a2c71a6c2bab5a8a17fff62', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-10-03 03:30:13'),
('tou2155082e179bfbf79dd012173614e6b3', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:20:12'),
('tou2186ec464b346d35c15f2a7d1e0a3f0c', 'itm_b5372f02c59649e04d0639862f151119', 'nologinuser', '2019-06-24 04:34:09'),
('tou21915854ff066553dc3b4aacec73e2be', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:32:58'),
('tou21cfac143512a059f9a0bc7cf393858c', 'itm_5445c989cdc31269853337a5c8bccf7c', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:01:18'),
('tou21d545701b974be8139c4490b8120935', 'itm_40c6367fee11bc95a223027f129e2bee', 'nologinuser', '2019-10-01 05:22:58'),
('tou21e789fe78dfec06298bb686fd4b30fe', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 10:27:41'),
('tou21ed46be22368d975be5dc5d29d08744', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:18:14'),
('tou22448f8c03a357f93ad186963b515c26', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:43:10'),
('tou2255234ac94425bf0ffce7007d60d2bf', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:11'),
('tou226b99e20d7ff67c3cfb68a63578555f', 'itm_c80942f3ecbc906985d16f32f4355072', 'nologinuser', '2019-09-04 17:48:21'),
('tou227a2478e370bf945dc13da133e04b1e', 'itm_b5372f02c59649e04d0639862f151119', 'nologinuser', '2019-06-24 06:26:55'),
('tou2283e026e629fb1639b8088e354ad091', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:23:48'),
('tou2288076dcf4dbd63518de09163ac4a0b', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'nologinuser', '2019-09-04 08:52:51'),
('tou22a64f53716eb11bd7e4c07b69b1117f', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 06:06:11'),
('tou22b81da3eddff45f4d3c78d921f5d060', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:25:54'),
('tou22db66bf71227c96d06f23cf01e743ee', 'itm_9ef57934867903960a9849bb7af133e0', 'nologinuser', '2019-10-01 06:45:07'),
('tou22e44c3cf5a9ab0f897c90ec7ce113c0', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 22:56:36'),
('tou22e52bae677ac41203f200120d063a54', 'itm_528c99e1abd619f814f246f0daf3f734', 'usr9c7a5d7887d53caba5ba4d94bf1b45d9', '2019-09-08 01:55:31'),
('tou22ebcd4b3bfc87261bc9be6e93de1587', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:08'),
('tou22ed40704c43c882baec6f4bff79381b', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:14:44'),
('tou230c90ccf49fc05c71ee4b0437896867', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 11:04:42'),
('tou230d246ebc6dcf2f91d973baa59b0fa8', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 07:14:05'),
('tou231665360fb7ae21bc05f58dd785c96f', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 11:38:57'),
('tou2357d4db83d042af3f41366c8ec909c8', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-08-29 10:41:22'),
('tou2366cf5a3f1d173197235a2ad2110ee2', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:24:31'),
('tou23899f65bbd474038f21fcb44624e218', 'itm_d2cbc2f461e291a45c6b97001902d1f2', 'nologinuser', '2019-07-01 07:55:30'),
('tou239e8bc5cd77f2e9be7adf8372626781', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:36:26'),
('tou23c55770758a4a60b47b808020cffb5a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:58:37'),
('tou23cfd41e26249cac365b0888ef8c7552', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-08 07:11:02'),
('tou23f3f4c7706c0b0158fdb4b1228868d0', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 06:01:20'),
('tou24225a67fe763dd69964b9a29c2872e6', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:42'),
('tou24370b4966cb553c29f6d9e93a9229a8', 'itm_92994f957013924861ccf81e6a8ea4dc', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-03 03:46:51'),
('tou2439a53a1262d7d5ba7d59c76f717943', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:38:31'),
('tou2460fed3721a783161b0ca203174f760', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-08-30 05:40:03'),
('tou2466482afa6fb020e46f58c1191a9abd', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 12:10:46'),
('tou247e378c00fac2e1e047e4de8c05dfbd', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 06:28:03'),
('tou24baad9e29e5b65a586e243740531bdd', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 10:29:23'),
('tou24c7abaa53ee25a059074927aff50294', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-17 11:28:04'),
('tou24cf08cb9017baf6b74f2e5668f7f5a1', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-09-27 11:45:24'),
('tou24d4ef049bf74051ec5d83e448b3e297', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:21:24'),
('tou24eb8346e970bc5e50cba06cf3257327', 'itm_9ef57934867903960a9849bb7af133e0', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 17:24:19'),
('tou24edd75a7dfff73a9278e4057598a40f', 'itm_aec300faa4929e3342679e55e63c24bc', 'nologinuser', '2019-09-12 16:15:18'),
('tou24f79de119e96fe0dc73f6bf1d974504', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-08 06:55:51'),
('tou250c19c6e98d04d947cdbb0aa7aa0247', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 16:57:37'),
('tou2518027cb7e81e0e7cfef8d86791ddfe', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'nologinuser', '2019-07-02 09:50:03'),
('tou2526273a6c8a6a08edf3030c232bb697', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usra81d6d42ece2694e59981dcb196fd09e', '2019-09-20 20:13:40'),
('tou2541ab86faf9d70bf5c585502bc62d62', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:48:41'),
('tou25548b20929f816c9b89562089ee69f1', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 06:38:10'),
('tou257163f5fe8c87ff8f39dc1bb96400a1', 'itm_45f53a2e646e484d6037e562f4233cfa', 'nologinuser', '2019-10-03 05:37:11'),
('tou257a380cf07c196959aebf73d29654e2', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 09:43:36'),
('tou2589e06c1ec887245aa553d3b57c1b77', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'nologinuser', '2019-10-01 10:32:04'),
('tou25be8338ae6238c5c05dc3622abe8cbe', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrae6a8c12f65bc61c727fe637f4a36b9d', '2019-10-02 07:44:49'),
('tou25dc93613bf0e9f927ccff66f8eddd54', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:01:09'),
('tou25f86f42b39bff4e2e4c8628745a4e9a', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-01 08:39:56'),
('tou2635b0aaf8799c05f6b438bba95ca29b', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:08:48'),
('tou26ada5a2f0c0d5042028d924f5949e35', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:33'),
('tou26ff8e6cbf16eb94d228402674d73fe2', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr3da9d16fefec62452ebcf01fc326a808', '2019-10-01 08:21:34'),
('tou2726c1334cb025d30fd7367c6d5d8233', 'itm_709970eef73e104ca5d4114b2485355d', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:33:03'),
('tou273b60f135d7c177070e8f9a79301e7e', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 04:11:18'),
('tou2754b05f91d4347e8e0652919710d7e0', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:02'),
('tou2764a047f29126d097672fb639b9c6fd', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:49:23'),
('tou27a38f5bc086eec1e169390348f12472', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-29 15:45:53'),
('tou27c17c2e5579844439783dd5cc6759d8', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:04:47'),
('tou27d0eeed783708bee6e560df94ba0f97', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-04 09:38:46');
INSERT INTO `bs_touches` (`touch_id`, `item_id`, `user_id`, `added_date`) VALUES
('tou27e6af281e9effab66694d72aedcaa02', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 07:11:54'),
('tou281d849934265ef80bcaa10ec3dab618', 'itm_d688553dcae9083a4104754662ec4d26', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 20:21:54'),
('tou282d58bf3ffbd6385df8f31183619e19', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 06:08:01'),
('tou2866cf4e4b2cf32dcfce6b2aff397536', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrff0b837879427f02dfc5fc4b6c49219b', '2019-10-01 04:56:45'),
('tou286a1a187060d0e7cf11ca98a23db1ff', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrae6a8c12f65bc61c727fe637f4a36b9d', '2019-10-02 07:49:33'),
('tou288910f04dab9d1a346aff6e9b627fa9', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-20 10:45:01'),
('tou28950b0f472861e443c80fa94edb13a7', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:17:59'),
('tou28a5ec49b0eb937afe911f698176324d', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-10 04:32:17'),
('tou28ac73212303037827f1faac521239c0', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 12:07:22'),
('tou28be7c3ac0603b151e6b27f4e95041ce', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-09-13 05:46:40'),
('tou28ebc32a670c8aa57cdcd161f7fe01b8', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:38:11'),
('tou29058d7cedeeaf93fb3fa85611c0e753', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:55'),
('tou29272c1b0c64e0b2abf164d2229692a1', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 07:10:57'),
('tou292bc6fbf0f2a016eed39af9d0f9bb21', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:27:52'),
('tou2937a116cdfae4c274c9b6d0872142d7', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 10:02:11'),
('tou293eb0de54bbf530c366171e36431bac', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:58'),
('tou29b14cca3ed9901d9bdbf898a7909f3d', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:13:13'),
('tou29dd035cc9c6e59135b2aa4b9a5f1b94', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:22:11'),
('tou2a233993f2e3e217504d7a8610419fa5', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:27:26'),
('tou2a27018f10886f4650cd110fc5d643c9', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 04:26:40'),
('tou2a79454470e70ad2d8085e6ecd98646d', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:08:06'),
('tou2a7fac7b8be49d603424fb5595139220', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-03 07:48:54'),
('tou2a94590c5fc93e57396a2de96dc5104d', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:23'),
('tou2ab639663628cc941e70ba6efc83ea34', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-01 12:25:06'),
('tou2addf555858b44ba49808c3c502aa91d', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 10:19:07'),
('tou2aede64e1cfeca72381dc3758cee7639', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 16:24:02'),
('tou2b00e94ba90b3a7451e969ac9ac223e9', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:42:32'),
('tou2b4a86a614b8fd12ca22a86d9fb4cc8e', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 12:10:54'),
('tou2b92c0170e99f95da9cef99e4980d057', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:08:14'),
('tou2b99c71f53f366ca346447516813b17a', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-01 07:05:36'),
('tou2b9a5242700bcb35f0243343b9c7fbf0', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:05'),
('tou2be061afa5ed8f3003d2bcf4ca85cece', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 10:29:54'),
('tou2c04c303e3b54238a81ea3a22f67e31f', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:16'),
('tou2c68cc600bfaa72f37c77b33f851f82d', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'nologinuser', '2019-09-27 22:48:23'),
('tou2c8486a0e97736cca5aa4df716a7ea6d', 'itm_ba0837310e18f29408f09fca748271ef', 'nologinuser', '2019-09-25 23:20:12'),
('tou2caa1bdd0bac18ab4e54165e0ac24a63', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-04 08:20:15'),
('tou2cca40032937df4eaa40081a158719cc', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:01'),
('tou2d05b419a411eacd4566aad8da240ec1', 'itm_52c74ad65651f004b6145559b25db4d5', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 11:44:32'),
('tou2d1c0b388677cb3fc25e45a4aa4827e1', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 10:49:43'),
('tou2d2901dee5081f923148443b776223fd', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 10:23:06'),
('tou2d694f59d62615141196d16bd8929b92', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 08:22:34'),
('tou2d6f96b03b550298f2bea2f9f46d9099', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 11:13:58'),
('tou2daecd4077892ede44c75c43bdf689fd', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-28 00:18:46'),
('tou2db02f8a7bf87c3c5b4e8920ee29a408', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-08 19:27:47'),
('tou2dbd8f75206a19d2cafe462e63b365d0', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-13 09:26:29'),
('tou2dc5024d94cd3e633a89516c1d831df0', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-05 07:40:22'),
('tou2dd0fbe18aad1109688e26b963346900', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:11:29'),
('tou2ddf5a889961fa1f550dfc3e0ca0e8d8', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:39:06'),
('tou2de03e3f8dcd6ab60e6e9b60c30918df', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:11:19'),
('tou2e18ad92d4f43263c260b4100d998d90', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-10-03 07:49:00'),
('tou2e18f06d441894d481025f2f3021c0ff', 'itm_df00498f0ac018c80372cef801513700', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:31:20'),
('tou2e23ce5888ffcf20d4771024a7dcae11', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 08:57:54'),
('tou2e294e0a9edff9b42aa8170302303ee1', 'itm_b5372f02c59649e04d0639862f151119', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:11:13'),
('tou2e36d563471cd9317909a2273d365806', 'itm_53e724271a6494108e270b923a2e8408', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:23:19'),
('tou2e4f154cf7e56ce862f290d54d423cf0', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-06-26 04:34:37'),
('tou2e6629d822d77385740c956f7da7b2b9', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-08-30 05:30:48'),
('tou2e78f8c66a41e4a7cf3bca490e38cc5b', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 07:59:44'),
('tou2f0ac6ee4608b18397327cd2be7a39f6', 'itm_d688553dcae9083a4104754662ec4d26', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 20:35:30'),
('tou2f0e48d254b90f93234a5bda03b3dabb', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:00:48'),
('tou2f3cc57cbb99ea2ec4c8da25970768f6', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:43:12'),
('tou2f4d29841d6f3402f3dfb77a55931197', 'itm_c80942f3ecbc906985d16f32f4355072', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:14:06'),
('tou2f58ef59da36246fb282f6154b924278', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-09-23 23:07:18'),
('tou2f5de0c315431431c627f454590a85de', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:55'),
('tou2fd2c2ab468a9b85d20b7163bf386247', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 11:35:31'),
('tou2fee95dd3f1a94ee0314c7fa673297ce', 'itm_3a197e7a07668e4d8580e5b129957107', 'nologinuser', '2019-09-20 06:56:21'),
('tou30113cc1115aa9f2fd6a5c385af5373f', 'itm_40c6367fee11bc95a223027f129e2bee', 'usr47d6cafdbced2e9935d62d18d2952cc7', '2019-10-03 08:15:00'),
('tou302e86c959528437310eff41c596d6c5', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 05:09:41'),
('tou303b2040fd4073e8b143e5248442a080', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 07:18:59'),
('tou304f51df930bcc9bffe39f91b1bfb545', 'itm_3574960a0f206a81307cfea2804acbed', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 21:32:36'),
('tou307c253348fd07a5a0a833c7f884474e', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-03 08:29:16'),
('tou3092c334a852c22b6ddc4eb20d06ed01', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'nologinuser', '2019-06-28 22:40:32'),
('tou30e2af8aefb184e2077a834b8d5d1902', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 04:40:18'),
('tou30ee129a195532771c28400d459bd9c7', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:58'),
('tou3124175313f63ea3371be6e9489f1034', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:15:52'),
('tou3128d819008241b2bd75e3b89ee4842f', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-07 07:07:33'),
('tou3154954aae985c94b567c670b36f4535', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:09:30'),
('tou315d5801f174512719e45f1c274c00c9', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 07:13:11'),
('tou31646556eb86709ce21797fdd2a14936', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:12:31'),
('tou31c30b9e4a5db8498d5007756efc84d7', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-27 11:10:17'),
('tou31c3e698638f61a0195ca4abc0b501f6', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-10-17 11:42:30'),
('tou31f19f1d0bf2be3cbedde09d5a66322e', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr802e5a133a8caaf0c428fa88c6bc9eb6', '2019-09-11 10:18:47'),
('tou320b3b6369eb9288b1dc523ca46fc194', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:14'),
('tou3239fa4a7f7a1c27881ca914e75d1468', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:47:40'),
('tou324af9400e7ab6af915a7a2de00aa912', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:17'),
('tou324e40f3526197170c18d22fa729559a', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:33:40'),
('tou32811a1cf480afb6024ba5cddd8e3823', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-15 16:22:13'),
('tou328892ad7d7f2e719d6fea44f2d897e1', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-03 08:36:23'),
('tou32d44b081d72cd22e86f601278ec5182', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:27:31'),
('tou3304b2f9da650e2d2b829339f030be22', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-29 11:25:48'),
('tou3327842eb756548459528da2a5fc7eaf', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-06-28 09:16:57'),
('tou33704db8d4e1123f492e121afae2ad1d', 'itm_feba75af4918ebee809bafb3c5e4c963', 'usr2143bfa9ba787c44d54468c26d7f485f', '2019-10-07 06:32:41'),
('tou33a3511d3230c621e93dc3c1a4ff9d6c', 'itm_8277fd5c824ab26ec25f902c7026a40c', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 07:10:35'),
('tou33c33e487daae6c48f8a56bd7b34bd84', 'itm_5058fb44b1f3a2efd9cbd0925ab67785', 'usrc58fe4f5fea11c19ee38cc1de75949ea', '2019-10-07 07:30:36'),
('tou33cd6c47602bc356f7f2c5bf41f99ea0', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 09:47:39'),
('tou33dc039a699ef14e48838f1230a2608b', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:58:41'),
('tou33eaa476ae15919d016b3eda015d8d92', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:00:45'),
('tou34263033d2a2bd05fc3a032e632ce528', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 09:49:37'),
('tou345dc9946bbae7d58d9a4f32d266ad5a', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-18 03:44:07'),
('tou349e6d80abaceaefbbbc6b75dfcfd66a', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-16 15:24:55'),
('tou34a1e752b00edf5744e6052a9a160b2b', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:45'),
('tou34cf92daec87cca7865e6e4d7e7d0ad1', 'itm_54709837e70ae2f179b49a739ded54d7', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:33:45'),
('tou34e8a855b0373cdd3f3119e9e4298c10', 'itm_10c058734f4608bb3b1b2c3b8a7853cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:22:28'),
('tou34f737e7a3d0ed2280528d902913539f', 'itm_2bb3fb13e1040cae112485657e31f868', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 09:33:54'),
('tou354056ff23460988386dc11679ac2590', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'nologinuser', '2019-10-01 06:50:17'),
('tou35412f494b722137ab5944047ecf1a2b', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:17:41'),
('tou35485938bf165e018cf495f5609495bb', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 07:02:26'),
('tou356b79960950c83ce15fbc02360c24cb', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 11:07:43'),
('tou35806cb00ea602c4663622225c806d61', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'nologinuser', '2019-09-04 08:52:48'),
('tou358148651748ba5c018db3f55e1c1eb6', 'itm_3574960a0f206a81307cfea2804acbed', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:44:14'),
('tou35b001f9cdbfa3d101bc185a71def293', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:23:56'),
('tou36011c2a3c0c30ac8263b4eece795d8d', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:13:46'),
('tou3602d0fc124ce5945fef36b7f7cdbc47', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:50:22'),
('tou3614657eb8a4359f1ba995eb80bbf3e3', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'nologinuser', '2019-09-28 00:23:15'),
('tou362bd1052533194a478215def56f750c', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-03 17:35:29'),
('tou364931ba675ff84997e0156a9c7c2515', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-14 00:19:50'),
('tou3650207dc69b957679a43301ab604e83', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-03 08:58:16'),
('tou365d1019b9c3c7cc2d6beb9a331e7d76', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:39:22'),
('tou3685bacaecc12ce9752bc70de5165995', 'itm_528c99e1abd619f814f246f0daf3f734', 'usr9c7a5d7887d53caba5ba4d94bf1b45d9', '2019-09-08 01:59:48'),
('tou369fc038a603da4881c010e437e46ccd', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-18 07:17:11'),
('tou370c8ef26327908eecddb0421d3d9c93', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 08:06:13'),
('tou370ffe31041a272c627092f34070f6fa', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usrf75bd4e3f445c1f676b725b971d77084', '2019-09-09 17:05:31'),
('tou3724ceb1743a7cdfaf59a2299492f808', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-16 15:25:36'),
('tou3730b43e0368f67008488c3eb9d04e24', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:18:10'),
('tou37373de230a590daaf8e5325b4a7689e', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:14'),
('tou3748ffc8d91d9ba5646e6ba102bb0ba6', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:30:52'),
('tou3754cb5774baafd96782a4c4042f12a5', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-25 23:33:20'),
('tou3782b7749f2706518f1a925bcfb17617', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:50:05'),
('tou379a6bf19a0af4088263fca0e308e57a', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'nologinuser', '2019-09-10 20:57:51'),
('tou37e43c1f3ae107b227173014ecc67302', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 16:15:41'),
('tou381a577ee1ed2c9771d5eeb0a89ea813', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:20:15'),
('tou3832e985a8dae929a355ccb938b5b7fb', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:50:45'),
('tou3842c5f24f5def6b2eb46f1624a5ce43', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 05:37:56'),
('tou3848cf78a32c0fceaad945cc0e945caa', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:26:10'),
('tou38ac494f4f3184980bb742c1a9dd1c70', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:33'),
('tou38d64d4e54e2fe708edee98c3e53845d', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:13:19'),
('tou392ea14a321aa7173d9e327235a908e8', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'nologinuser', '2019-07-01 06:21:02'),
('tou3935185e92693a3d310acef3ea7f5121', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:25:37'),
('tou394c50f2fe4770240583bb1bf332b83e', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr86c8c0493239465a8a7d920112fd2bc0', '2019-10-01 10:40:11'),
('tou397468c902321cc67006207705b5920b', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 10:09:01'),
('tou39756b1bd30e85b0aa8401c53c241a68', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:12:13'),
('tou3985c8fdf382b0a666c5f630fdcf3065', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 03:46:20'),
('tou398ad862d174c1882f9399a04f17fda4', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:18:16'),
('tou398ae774fbc0f93c319afaffc246be49', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:35:59'),
('tou398d9f65f9c2add07d276f6d07661ccc', 'itm_ba0837310e18f29408f09fca748271ef', 'nologinuser', '2019-09-12 16:13:38'),
('tou39ab4503ad362b2167ad017b821bdd3a', 'itm_b5372f02c59649e04d0639862f151119', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:01:42'),
('tou39ccc5109ea94af1ea20d88a6abd4b93', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-10-04 09:03:18'),
('tou39e06297d2ae94591eec850e38ca7684', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:51'),
('tou39fbe0bd100d8ee1bd88fc2009c0998e', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 08:17:43'),
('tou3a066149113ac0dbe250fa7034921d13', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:50:23'),
('tou3a09bf0f976f7ce6ce681f305ad1d8c3', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:24:40'),
('tou3a5cac0ec00e2e3b4954f0d34099a2bc', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:37:02'),
('tou3a97eff4857af0693aa6949858ad45dd', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:54:38'),
('tou3b007b36b129b6dcda0a5bd8ca4f2008', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 03:57:47'),
('tou3b1a6e93a97ea872b6b3daaa074c12e6', 'itm_7359de3d150f84bd9e06d9e6054ba151', 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 07:40:52'),
('tou3b27d2415b1cc1a873509601291e06ec', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-07 07:08:18'),
('tou3b522881b269118d1c6df1dfe796446c', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-26 12:22:36'),
('tou3b59edb71b6f1ed0283a9340be21cf97', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrba287c1864e60a6def65e8fe3242d906', '2019-09-11 05:16:42'),
('tou3b92eb1e2ccf65520142475c526684d2', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:55:49'),
('tou3bf6566c561afe08edee769e890d8d9e', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:35:48'),
('tou3bfefbfc332b0a117426da68399c2fe5', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:32'),
('tou3c1f39914fdb358068aa4f66fbb2c94f', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:47:20'),
('tou3c2866b2607021c3c6f5dfcbd291f46d', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:39:59'),
('tou3c43b55322aee69b37bf95d46aee2893', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-14 04:03:38'),
('tou3c550aabdd930a0913e64518fbad8ff5', 'itm_40c6367fee11bc95a223027f129e2bee', 'nologinuser', '2019-09-23 16:23:52'),
('tou3c723242d3a12fb5faa5889238e3b5cc', 'itm_2bb3fb13e1040cae112485657e31f868', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:17:28'),
('tou3c98eae93b7dbd5fb991096d2ec6c1db', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 10:19:05'),
('tou3c9df749455ba350e7746fdce727a059', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:00:29'),
('tou3ca91dc18b7b15862781256ea2c7baa5', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 11:57:08'),
('tou3ce4103f9a58021a6401d9a9bb125fdc', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 07:18:27'),
('tou3d0103d800830d56295775cdb91c7710', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:31:53'),
('tou3d366d7d889cc99a63a21cfdc1dc2dd3', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:49:46'),
('tou3d95a4b43bf9b7c439accae39367f1ba', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:47:11'),
('tou3da87307798f47fbd0776ac9fc66fe68', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:54'),
('tou3e0541d48a2e9e3517a1e76bbf85934b', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-25 05:21:07'),
('tou3e05cd0cf0657ea3427a2d4a0eec2172', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:39:27'),
('tou3e07c681f03ea929c726d44af3eaae05', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:49:46'),
('tou3e465e62f1b97bc4ca7ecb461a64a0b7', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:35:21'),
('tou3e5797774ffc4f24ced47dbbd5118d69', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 03:52:23'),
('tou3e95fa003a934332dcc0e1b222ed55bc', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-08-30 05:26:18'),
('tou3eeb35cbefa6e219b5ba63e42f7443f3', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-27 07:53:51'),
('tou3f2d51b3fee416257a479f57b9262c6b', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 09:45:26'),
('tou3f3625fddcabb91b8bcabbbd1ca1123a', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 05:54:34'),
('tou3f51e2a82bf2a8dee85d49e1843a010a', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 05:51:32'),
('tou3f6a7015d285ab4d0a3b1885954b834e', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 11:04:31'),
('tou3f89d4e01490f4c3c0c121f213bdf96f', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:13:01'),
('tou3f9b3ed5d4d2b399929ab132efbb33ad', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:27:36'),
('tou3fc137d2ba668e5ac46081ec71a461be', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:29'),
('tou40211259ceec8f586314b09f7ea79a02', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:17:01'),
('tou403ee482a4f5f266105cfc2945235229', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:29:04'),
('tou404c3773cb8afc450270b203b648e0bd', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 22:05:31'),
('tou4078b13615ce8eb1f16777b00325647d', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'nologinuser', '2019-09-08 16:26:34'),
('tou407f8ca6565b4a31a24591fd22af9db5', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'usr7e1473b2561c8c5a5b2fc2a0b4c3b732', '2019-10-18 05:37:14'),
('tou408fd3f90eff4fd8e8092244be5859f0', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-24 04:52:40'),
('tou4091e85586d19296909ad650f9b46d00', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-26 16:39:23'),
('tou409dc5b0645946550bb27823af64db25', 'itm_52c74ad65651f004b6145559b25db4d5', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 10:42:57'),
('tou40a945446eec0fbbf1e9023cbf004bac', 'itm_228aca47caf96513d300aba7aa9f2fbb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 11:34:45'),
('tou40d844461af989c506eeeb48426446d5', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'usr786aa1c24816cf370ecd666472d8b548', '2019-10-15 17:54:00'),
('tou40dc53ad37b78fcc0cf29baa1fe96a88', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 11:11:26'),
('tou4102742f45f660b36be943acc0e39685', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:47'),
('tou4108363c594258aa352ffa892a7f9489', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-09-28 10:12:20'),
('tou41366854ac989a502bef9e5ed1dc9ef3', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:08:03'),
('tou419f1167a37ae0584ac76c0ba3af638c', 'itm_0580d078a65d3887457b4941033ce226', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 12:04:24'),
('tou41a651f1761cc5701a4dcc163e3fb2cf', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'nologinuser', '2019-09-12 16:10:24'),
('tou41bfd7636b89d399754570a063368fc8', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 02:58:08'),
('tou41c2924ecdd454aa6ceed40d50858c90', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:04:29'),
('tou41d274773b28d11e311c86ecf316c93c', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:53:39'),
('tou422ae414fa99c89d3224fc227a5b0040', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 13:15:32'),
('tou426f33169dbeb06e5ec7e202cc6e5ec1', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 05:50:30'),
('tou42d199dd51e17ff8a334d67e6452bed7', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'nologinuser', '2019-09-07 19:52:00'),
('tou42d8222632859766fd40ac36ef76f004', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-04 10:16:32'),
('tou42de147950fd8d50a6130a1e4ebb8fff', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-09-28 19:21:14'),
('tou42f0eb48aabdf7a7b562a2a33a711d7b', 'itm_d2cbc2f461e291a45c6b97001902d1f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:17:08'),
('tou42f412772a8bc7da7dfc008c0a48d533', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 06:59:17'),
('tou42f44ae824568994080252216532c802', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 09:08:12'),
('tou43012f21eafa5e6017972fab3e32dada', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:11:29'),
('tou431248f1c6c52d3bb93360bdaebc685f', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 04:27:50'),
('tou43199bfc1b52b1d73f9d9d5c4d7afa83', 'itm_5058fb44b1f3a2efd9cbd0925ab67785', 'usrc58fe4f5fea11c19ee38cc1de75949ea', '2019-10-07 07:29:45'),
('tou432e7c9c13f28f38998614900d196e79', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr9c8be3fbd3f169f5015105a9c44f7d30', '2019-10-02 08:06:21'),
('tou433cf51ba1e097f49bd082c1b4595a6e', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:44:44'),
('tou437ff1fcd2b2f64ab6a8643b811f3b7e', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 10:49:56'),
('tou43953c72353a9c8ec079e71e06805a23', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:39:04'),
('tou43aeddbd0ced4152407bad9bf43c0b8e', 'itm_9ef57934867903960a9849bb7af133e0', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-15 16:14:56'),
('tou43d1d9cc5822d4dfbc4b8ab7a00a9bf8', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 21:08:26'),
('tou43fe641166d2d7d428669946df29332c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 06:58:53'),
('tou44150ecb3fd0904469c20a256925a55b', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-25 05:11:56'),
('tou448d3bbf1d0c5fbf3c665ac5c3897561', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 04:11:38'),
('tou44a1be55b759f5fe372ec0363b5c2848', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:54:17'),
('tou44b12bf8dc94efc8553e22f9d12b3506', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:36:33'),
('tou44d2777d9bcf606315b3c7145112a4d7', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-13 03:07:08'),
('tou44d98140af3cd7b5bab6a23dd81f23ae', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:31:53'),
('tou44f6132ccd5ff2eebe05f514c5609315', 'itm_d226e2115d5632e9aaaac89bd880737d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:21:35'),
('tou4531a3d96501112e737ae952fdd4bf9f', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr1c30bc352b4dd71bb0469d1b02a65da3', '2019-09-19 10:48:33'),
('tou4546193a92b98ae35261c95e0e72f0f9', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:37'),
('tou4546cb6fb1addf162ba65ed94acca4e7', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 09:48:59'),
('tou455965a795535a133ee239cf5c28e845', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-12 16:09:25'),
('tou45a12467bcb42c64cdd7ccbc1017f327', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:28:08'),
('tou45b0d66ad08050f4af39b203532b19da', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 07:12:23'),
('tou45b2113296a600d40b19cefbfece56f9', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'nologinuser', '2019-10-05 05:12:15'),
('tou45c29cf49cdb9f02a91538534cb66212', 'itm_228aca47caf96513d300aba7aa9f2fbb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 11:33:26'),
('tou45cd7b096d3478b44d7e45c706fa5635', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:20:52'),
('tou460ed92880d40e20920aafa0ed8f8e43', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:09:39'),
('tou4624f2eb742f9cafbbfd28beebc2294d', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:22:52'),
('tou4626fd53dd04a1f2e867115a852236ad', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:27:34'),
('tou4637867a8e222fb010915f60408a832d', 'itm_19d8a632ca279d2a4739913172cb71dd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 07:33:22'),
('tou46ad8d02cabc8c9dc647971defd546dd', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 10:20:28'),
('tou46e25932e2ac5a71a7968ec6c6849d02', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-20 11:52:22'),
('tou46f64cbd8e9cd226402585ab106dff05', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:24:12'),
('tou46f90e52835301dd2b376ad1d6471bb1', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usrebc31b0e398a284d3c6552dca089d3fd', '2019-09-27 04:29:57'),
('tou476914357774116e2aac6df3fd0288af', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:27:08'),
('tou478550939d8cbb6bbde681204e170f13', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 06:06:32'),
('tou478c334e7f7ecfd1c3b898d5f72a0373', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:57'),
('tou478c367273f06562067b60302909d9d8', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:53'),
('tou47a66a57a55834e9ab5821484f085188', 'itm_d827ad45f61ccd9df35e24c1678ea6c6', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-08 05:29:50'),
('tou47b793294f26efc6c953be45e4ec2b08', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:19:58'),
('tou47bd32b05a8c9f9b6aec6741912d8f95', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:00:56'),
('tou47d6d5ccfcfa5c655ba92b44605772e6', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 04:41:35'),
('tou47e268fe54476a38ee17fb20cc6cff69', 'itm_9ef57934867903960a9849bb7af133e0', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:22:20'),
('tou480da3ef22a15759d079ffbcd47cb827', 'itm_b5372f02c59649e04d0639862f151119', 'nologinuser', '2019-06-24 04:34:06'),
('tou4820847293cc5dd11d0a5eed8c481e29', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 07:12:01'),
('tou4830c602e420fa2633b032cf8bef7b94', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-10 20:49:44'),
('tou4838e913df846c12e9286ade77126408', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:33:42'),
('tou48451053482160b7bae0b43ca2915bf7', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-10-02 11:36:52'),
('tou4854b5bf9b2d83bd9f41919338003049', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:36:58'),
('tou485b98160001a33a4e5f229c6a3ffc8a', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:51:59'),
('tou486e528a301b3dc6ab6b77a5572f48b7', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 22:46:28'),
('tou4871d7becf19b403e8400dd8a1c1e3c8', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:11'),
('tou48b7cf42ddd2da7593beee68e91d0b89', 'itm_715fee0a325de85667e2271e03d8c30d', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-09 13:54:14'),
('tou48c1fb5c086858dfb607cf1de934b81f', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:43:57'),
('tou492a1fe76924a3f50f86a9ae75e68542', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 07:09:38'),
('tou492f0ff1768ed067fe10629f3c74f8c9', 'itm_b18cca37df85a01418739145d4369153', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 08:08:10'),
('tou494237a30cc151ef46bb370367e37076', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:23:49'),
('tou496b27561e2f18c8539df8641a4926d1', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:11:33'),
('tou49cface4539f45f8081cc24435b90453', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:48:18'),
('tou49ff939359473da78dec1aec5120b0ae', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:35'),
('tou4a2cc28c5dffe71105a4dc000f9c59fc', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 06:11:38'),
('tou4a57b6d918e99237d2d9f01ac8f3a27f', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'nologinuser', '2019-07-01 07:58:57'),
('tou4a6c5563def6e3a08f5329f241dc6680', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 05:42:50'),
('tou4a71a727fc8df92cf0c6582da9a3e06c', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:07:48'),
('tou4a82049e9f660ea059c096bf4ad6cff1', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:31:16'),
('tou4a9aa26a0f51d64ac1e5039378b86326', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 09:22:02'),
('tou4ab57c43cac282c9637671806a268a22', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:15:32'),
('tou4ae7b19f5f7b6e90f3e4a749c06ec908', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:32:04'),
('tou4b0e4becda749561622fd85ad678d4dc', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 05:04:25'),
('tou4b1919cd84ae8290bb973a2f66bafa21', 'itm_e484812d33f6ca0a25e3143683d67688', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:27:46'),
('tou4b1f9fc7187f69dc7294846c2d6f1db5', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-04 23:22:54'),
('tou4b3d33c285f66f4b64d219b52cacb10b', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 09:27:43'),
('tou4b4f3b4b5a7fb1dff402b61ad5ea1f80', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 16:25:14'),
('tou4b5c1f022b0d7e8169f8bec702a602e8', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:24'),
('tou4b6acf13967087f73ae28c5c9ea32e51', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 04:25:46'),
('tou4b99cbd5ea0248afb005cf3f2a3e3533', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:30:51'),
('tou4bba53c8e0832c47ef18a0be52f0cc98', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:59'),
('tou4bda72c13ce45979b930de1a23cadc21', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-22 09:29:06'),
('tou4be5b690f270ed214046a2ab37f09d05', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 17:04:02'),
('tou4bed0d0634b2f6ed8c37a8b83717e283', 'itm_249cad8b21c392388438d2d806daa3f5', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:27:59'),
('tou4c4874385966677fc52a50bb31337afa', 'itm_b5372f02c59649e04d0639862f151119', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:00:06'),
('tou4c4a69964477610125246a7da76d5f57', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:47:11'),
('tou4c5d40014693fb437e35b09b838d1f36', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usrf75bd4e3f445c1f676b725b971d77084', '2019-09-09 15:51:56'),
('tou4c60239c36e63fc9971787eda643247d', 'itm_1a7f3815a51c3c3888ede85bbacb9b88', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 07:09:33'),
('tou4c81c918aba3e7b8d0702f3ec296d826', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:25:38'),
('tou4c9b93fe58f0a0ed7adeca95f26fbe7f', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:31:48'),
('tou4cacd3d6bc74d5a9aa56c026c5f9e2de', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 10:36:23'),
('tou4cbcb54499d1bb569f705bb826507e8a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-29 10:45:04'),
('tou4cd1c6730d0fc2ce26efc971e88309de', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:22:42'),
('tou4d13a54d73b28fb2934b68667efe203c', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 10:13:37'),
('tou4d289ca6fa54e329c941178d28a28a3e', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:43:24'),
('tou4d473f0431500c38cffd34e32085b349', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-04 08:13:18'),
('tou4d5a5420fa24da63894f39ce7ffb91e6', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:58:46'),
('tou4d71395e33e9aae7138f064bbc72278d', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 04:15:37'),
('tou4d959b4296700b6715da21052e26d4e9', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 09:40:18'),
('tou4d97e91a12f1a5c8e742a8a8852ea35d', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usrba287c1864e60a6def65e8fe3242d906', '2019-09-11 05:18:21'),
('tou4dad9ae1e8d2c16811049a3e1ead8684', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-14 04:08:51'),
('tou4dc7ea9f6bb4afc20876fe0b5aad5ad4', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:20:58'),
('tou4ddcb26c476cedbcedd8980280d4ffeb', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:30:34'),
('tou4deb45c0e5ff105942921aff093416ef', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 07:29:30'),
('tou4df14d7842e22747db65d1adbfd716a3', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:30:14'),
('tou4e0e148d4bb1f73891f7f3ed78b6a47f', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:01:50'),
('tou4e4af4f240d054eb36740cc92c6a6235', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:12:40'),
('tou4e63311c0fe8fc15a59e75993dfe020b', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:33'),
('tou4e935507429be4c0757d0dc79f4a9e69', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-03 07:16:46'),
('tou4eb078fe8a345887cec44b995c4163ab', 'itm_3bc2dd9f6f7dbfcefb5238ae4a6ddf2c', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:10:08'),
('tou4eb9011bbbb960d8f4a54da67bbff154', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr8ad862ee8b0eb6495d389cfe6f35655b', '2019-09-17 04:55:07'),
('tou4ece756637f5e3d961ae15662355b2c5', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:36:51'),
('tou4ed1627d73f621adaac4027c216ad817', 'itm_40c6367fee11bc95a223027f129e2bee', 'usrebc31b0e398a284d3c6552dca089d3fd', '2019-09-23 08:52:43'),
('tou4ed6caf9cb0e1f6cdee87c571b105e53', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrf31597aca589e0f1b43061cec1f399b2', '2019-10-02 07:02:57'),
('tou4edba95c7e7d3960a446536d3cd9e855', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 04:18:42'),
('tou4f051fd7ba4e60aace7ab42f73647560', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:00:43'),
('tou4f0587f7ccc8cf050b76af245f3b8581', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-27 07:53:55'),
('tou4f0d79f058a6aeca7a56b26dbee491e7', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:24:56'),
('tou4f175547408e6d99378957788d71ec95', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-08-30 04:52:33'),
('tou4f383f3b885acd049217bb9d952e6fb1', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-25 05:11:39'),
('tou4f39f8d22c8489d2b247c6f35815b6de', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr706a71c3e5bb7b0f5297fc0ec706a61f', '2019-10-04 08:54:07'),
('tou4f475e7e7fd2a7a9cf5e37a7463659f7', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr689a884fbfd8ce299702581c91dea537', '2019-10-01 04:16:33'),
('tou4f7800fb1bd0963079d93d738f376707', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 13:23:01'),
('tou4f7a93f91dc06a89ceea8c1b5e95da5c', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-27 07:53:55'),
('tou4f8e578eea39b8925a1418982255cce8', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:08:17'),
('tou4fa3490e884cd23337bf949ede312764', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:01'),
('tou4fa5c670199cab9ca63233a1b452fb92', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 10:29:55'),
('tou4fa6e1246b0d452486b1b30bd8c72f96', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 17:04:55'),
('tou4fb8dd4acce3dfeb3be8796b377d6dc7', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-04 05:00:24'),
('tou4ff605512c4d87fb1083eb951d7f704d', 'itm_519b7a8c4c4d29e4130548481ca6f9f1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 09:52:58'),
('tou500ad257e0e14edc80c5bc6668cfdd28', 'itm_9ef57934867903960a9849bb7af133e0', 'nologinuser', '2019-09-18 21:12:03'),
('tou50104d04e53714144b8982b0ba52d5d2', 'itm_715fee0a325de85667e2271e03d8c30d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-22 12:33:01'),
('tou50145ab61478bd6fe90561cbf64a1764', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 22:13:01'),
('tou502dd9dabcaebde075e8f12010606ee0', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 12:02:15'),
('tou50b436d740fa65dd68f167446a7e727c', 'itm_715fee0a325de85667e2271e03d8c30d', 'usr786aa1c24816cf370ecd666472d8b548', '2019-10-15 17:53:46'),
('tou50e9f56462e0f1d35cf23ff216ed51fe', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:08:10'),
('tou50fb3e5cf98d496b037373bfb079b58a', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:25:43'),
('tou51164235a6a2ccb746cd9864bb0170c9', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-06-28 09:44:33'),
('tou5120a93298d5f3bab57b228f09edc47c', 'itm_40c6367fee11bc95a223027f129e2bee', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:22:35'),
('tou512fa92d4c63fdc6298d973d70115561', 'itm_d2cbc2f461e291a45c6b97001902d1f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:17:48'),
('tou514fb522d98e1dd0ee5727938adbb17f', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:54'),
('tou518bd56ab03cfdea7b8382e3cb97cef1', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 06:12:00'),
('tou519ea239529e4ddcb2e2ccec3549fedd', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 06:36:02'),
('tou51abf4691b5a83aceb1da5a0736328ab', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:45:07'),
('tou51e6d159c5652b919c260207fda38323', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:25:43'),
('tou51ea9e734d29c8b4cd2301bf2fa50abe', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 11:46:57'),
('tou51fe0991b3b5ab03c5c822d6ce0a7541', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:30:43'),
('tou520c81dbee7f1fb6316ae1c26417197a', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:23:35'),
('tou522069651b2d5804a96939b1d25ced56', 'itm_715fee0a325de85667e2271e03d8c30d', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-09 13:53:09'),
('tou525106bb3c600aa71a435d1e638967bf', 'itm_b18cca37df85a01418739145d4369153', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-02 11:41:31'),
('tou5256cc25b93afd7329724d49e8cd4300', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:07:06'),
('tou525da090a27efaf465cfba9c7f401a8c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:24:05'),
('tou526c94a356e6fd43833adf76a3ff3a3c', 'itm_7359de3d150f84bd9e06d9e6054ba151', 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 07:40:18'),
('tou526d91c3ffebfa2af45ca16b23a2c32f', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrc4ee43195313d1c2d59e57828d61dec9', '2019-10-02 07:13:01'),
('tou52d7a5885df5ceee29913a127db40c09', 'itm_caf91ce095ca25085c5f89a45a7cdd65', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:23:57'),
('tou52e3574f9613d1c58bac85c6f0ce5cc8', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:23:37'),
('tou5308e5b71f09e433c3af43e7bf05a9e7', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:34:21'),
('tou53165913ac53afe40b143666f95c5987', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 13:14:19'),
('tou531cc042186594d89c6f00de04949feb', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-02 11:34:14'),
('tou532c9c4efa09a9677c680a2ce1e6ccfe', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:01:09'),
('tou5359227317601d7773aae58fbe8f4b1f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:09:46'),
('tou5365eeb8f1ac54d9d1fe131af31d05a0', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-02 11:30:26'),
('tou537d7a54ee6458f654d80df6a4bb1e0c', 'itm_82fc8ec356881e51c9a88e15ae38f339', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 03:29:51');
INSERT INTO `bs_touches` (`touch_id`, `item_id`, `user_id`, `added_date`) VALUES
('tou5384449c6f8ebf8ea3c73d8f1a882822', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:17:30'),
('tou5399a4fb8fac104a50226f2acd5a44e5', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:36:48'),
('tou53b63203df00ef3fba850725ee2c99b3', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 10:02:10'),
('tou53bc18e014393b463d54381af3c974a5', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:13:26'),
('tou53f6bfb2b436dda0229c83d802c1239f', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:10:36'),
('tou54cc745b56b224ef473a157ef0ca4c4b', 'itm_3574960a0f206a81307cfea2804acbed', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:41:56'),
('tou54f6818b17ce88da348cfc71d483f88f', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:55'),
('tou550b41d6f41af66de367dba79a1f682d', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 08:25:54'),
('tou555abef6ed9a7f6df379d02815547650', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:20:49'),
('tou555e2be1b0320af2e4cd425d6b366b9f', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:27:57'),
('tou555f117aea9ad1aaa5913fbc616d8b55', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-30 06:13:43'),
('tou556715e5259965506f9cb1f99739d289', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:36:26'),
('tou55a1f770efcd7c8baeceb0c73a8cd100', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-30 03:46:12'),
('tou55ec4d2533e0098693425c69ad35c9de', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 00:33:52'),
('tou563acee3cda64444d3f55cd07a17862c', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:39:59'),
('tou5644840c6a8a9143c4b119e586639550', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:11'),
('tou5651192ae1db4c2ce2a05d2b3171aa29', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:10:23'),
('tou566a219fbf0895223957e77dbcb78067', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:08:58'),
('tou569cd9b9b0b9fa4c2f96d26bb48277e5', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'nologinuser', '2019-09-18 03:53:48'),
('tou56ddcbe09ebc0b38a4b9dd5a4cef453d', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-09-13 05:09:50'),
('tou56e084396b2675d4a7344ce66f0cf0e4', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-10-03 03:30:13'),
('tou56ffaa1f6d4fd8df85b87fe29e61c360', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:36:23'),
('tou573ced820f710ab13dcb9f9632bc6cc6', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:53:07'),
('tou5761b821119df88a9f0f5da29ae9b568', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-10-04 09:03:24'),
('tou5790fccb7e774c72cbecb143ba0fc55c', 'itm_aec300faa4929e3342679e55e63c24bc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 00:10:12'),
('tou57a06987ef705f0af02c61b89ec5bd3a', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-10-10 07:28:49'),
('tou57a5b98cd8c1c6d709c47a5024957a55', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-09 13:56:04'),
('tou57b2e40395a294dd3225c153cdaa0285', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 07:02:07'),
('tou57c47b8e5add1162d0543ac0adc5fd4b', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:07:04'),
('tou57c5e4cd7a3f272ae21649a09611d091', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:54:41'),
('tou581ca3caa4023fd83a40a377e6584965', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-26 07:26:40'),
('tou585a49880c058d50efd5ed1c8ef11e02', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 09:43:54'),
('tou58757aa08530152c7b9d79d4ff0124f4', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'nologinuser', '2019-06-28 11:39:42'),
('tou59293d294c48e822b789489f88caedd1', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:32:40'),
('tou592a958e8463aef500a8484044c3effd', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:17:57'),
('tou594d292dd58e1e786236d15194cf6b6f', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:39:43'),
('tou5956161be9c39632bd22e623400261da', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'nologinuser', '2019-09-15 16:13:05'),
('tou59805a44497fa42adc1684c469b642e2', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:30:54'),
('tou598490b14b1d281aa709edc87433af6c', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 20:13:42'),
('tou59a9e656d20fddb74acf25ba759573bc', 'itm_33a66429f209514b7fd2c128fbf1f88d', 'nologinuser', '2019-08-30 05:40:19'),
('tou5a2468295b7317fabe4d9264d9c729a4', 'itm_d2cbc2f461e291a45c6b97001902d1f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:32:12'),
('tou5a2ee47af30bdb47bac6106016bfb254', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:11:00'),
('tou5a39262488108a89d4543ab96aa64756', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:45:03'),
('tou5a9120dd2c7fb50417483f4af34599c6', 'itm_aec300faa4929e3342679e55e63c24bc', 'nologinuser', '2019-08-28 15:31:49'),
('tou5aa095f48ad22a3a9352b37ba9794836', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 03:20:37'),
('tou5acdd1df724b5bbc15617155099cffae', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:10:07'),
('tou5ae6523b172082cf55773570f66c3e3f', 'itm_79324075063082372ebcb5b759b2bf7a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-14 13:55:54'),
('tou5b03ba50ce5467c40c13776287236767', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-30 23:18:25'),
('tou5b174cfb6b5d7202aad108f5b4641f56', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-06-29 08:21:33'),
('tou5b468b783203044a3a57ac73514ceae0', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:20:30'),
('tou5b60e0885d5efb436526566a83f943bb', 'itm_aec300faa4929e3342679e55e63c24bc', 'usre5cf4f22a2bb0ad81a5d8b492bdfdc05', '2019-10-07 06:55:42'),
('tou5baf4ae79a2cb7ceb30eac6ddd8ae0e5', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 13:14:32'),
('tou5bbbf37ca9aa7ece32da27ccfc2be020', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 04:41:39'),
('tou5bcb05b99b7c229a44a2d7ed44b72e04', 'itm_f435477e7d52baac496a0be7621974c5', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:31:24'),
('tou5be1bf908f4c571f99af4e99a6d5d7e2', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-04 10:16:24'),
('tou5be29ebfce4a12e0ac85fbb62dfd9916', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:06'),
('tou5c3cafcf48464c933f25d231db019226', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:30'),
('tou5c4d622acdf0fbbfe2cfd1830d410007', 'itm_02db30c4e6a968ac1955cba086d95b17', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:23:45'),
('tou5cacef6f1714541b51488aaeadf40db7', 'itm_45f53a2e646e484d6037e562f4233cfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-30 13:52:15'),
('tou5cae0919727eb07cdc92ce1ca1a1d800', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:03:17'),
('tou5cbe58b9521b3b13271a482a28f88063', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:07:04'),
('tou5cd4a97c33a9fff34515c6c835fdcf45', 'itm_9ef57934867903960a9849bb7af133e0', 'nologinuser', '2019-10-03 08:24:22'),
('tou5ce49f0ef17370ed53b51201c803ae55', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'nologinuser', '2019-10-03 07:52:49'),
('tou5cf211e5732da9d343286d2a6c40eff3', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-10-13 11:03:00'),
('tou5d0dce0e174d95e8c778446628eebd31', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:57'),
('tou5d3756f9044ee07f20adb54bba261d56', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:36:45'),
('tou5d4ff896a23b9f6959b87499a98bf854', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-14 04:03:34'),
('tou5d8d9f32b0d23439594f55a8ecfeada6', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:58:55'),
('tou5d8f69bbb819ff81cbcc98f24ab02299', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 10:49:48'),
('tou5db46158a3642c3bba6a56764d796812', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'nologinuser', '2019-06-27 04:30:44'),
('tou5dc7f89fa8c51b315e9071fc679da542', 'itm_feba75af4918ebee809bafb3c5e4c963', 'usr2143bfa9ba787c44d54468c26d7f485f', '2019-10-07 06:33:46'),
('tou5dce7622131765d59cc2df4295843917', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-20 10:44:48'),
('tou5dd208f212a9e9d0dabc6cc4629679a9', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:16:15'),
('tou5dd7b8afca2e23d1c2c38a41efbb70e0', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:05:57'),
('tou5ddaf428f8b47da13022a0ec5455076f', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:18:45'),
('tou5deceba4abe0bca389c853188ed48e14', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-02 11:24:31'),
('tou5df9ffbc94453efaee92120014a553d2', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 12:40:42'),
('tou5e05b9bf69d344e18a7a7026411a80cf', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:37:00'),
('tou5e14d6dcb6a20a10b0017a18e22683f0', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'usr20639a91cb16086c6cffc404e7744fb9', '2019-10-01 10:37:14'),
('tou5e268750ae271035c1fb6be35dfe2131', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:27:22'),
('tou5e3bd6c2c10a69e955804eb78e0994ed', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 19:41:55'),
('tou5e405bec00e299f6f73a8bd3cfb4130f', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:26:24'),
('tou5e531f66a47d09e73e9474ee8f307f9d', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 05:25:39'),
('tou5e5ec3d75380fea8dfee114587837081', 'itm_caf91ce095ca25085c5f89a45a7cdd65', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 09:37:19'),
('tou5ecccab1cdcc663e5bb44448ba248c28', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-06-24 05:13:51'),
('tou5ee50d303e2b6ef67010cfcbc7d183bb', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:15:03'),
('tou5f280fe05ef400b02ea2e8e0d8482dad', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:33:20'),
('tou5f2b0c4a2868cf3af95ad3fcad460e0a', 'itm_92994f957013924861ccf81e6a8ea4dc', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:49:17'),
('tou5f2dfc6cf07b0de595545ba76fb17731', 'itm_b5372f02c59649e04d0639862f151119', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 11:10:28'),
('tou5f6ef0dde68b5f49ef998eed1203f5d1', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-09 10:48:53'),
('tou5f799c568b66cd9fbb9dc7851e552fff', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:11:57'),
('tou5fc8bc32abd34f35ad3aaffdb4a171b8', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 05:05:59'),
('tou5ff2658cd6b4104b84ee3dfc68ce67c4', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-18 03:43:48'),
('tou6022213039d234a071339967676c04b1', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 08:12:02'),
('tou6052403a4698e5f9562f9126efe64095', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 07:13:42'),
('tou611d16e98b50528f29ff7ce8ddbc4457', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:47:24'),
('tou613c90244d2efb2ca8e58745dfb4722e', 'itm_a65be83fb825406c14760f8848ff7bfa', 'nologinuser', '2019-07-01 07:55:48'),
('tou614537a915cce79f642437fb05c7435e', 'itm_478864abde0d4c253f54cc5eb3a68619', 'usr1db69cc9b7c316666af1bb9d623e031a', '2019-10-07 07:51:06'),
('tou6154bca7a797823aff49ebf2b4b20944', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 11:10:13'),
('tou616a9cc054531b3af772deeba91b5dd3', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-11 06:41:41'),
('tou6196348dbcfa50c174cf6ccbf5fcebaa', 'itm_3574960a0f206a81307cfea2804acbed', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:44:14'),
('tou619e2c6e142c752023f514da9c1da610', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 03:34:18'),
('tou61c3fdac62aefffaf514ab5c5c4656d6', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:50'),
('tou61f80379d9735adbb5886493e3af98a3', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:11:26'),
('tou620530564bffbafb1587f972b914f189', 'itm_b5372f02c59649e04d0639862f151119', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:07:55'),
('tou6212fa3ec44f5ad2b2fdfaf603fa3c74', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr8f466efaba2c555c63f6b739db4f6f6d', '2019-10-07 03:59:02'),
('tou6217ee68ce7db4f930340774941c21c4', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:36'),
('tou6236ac0580c7afea7c8d440ea5480b8b', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-12 18:46:11'),
('tou626b9cf763129b96532b3390f88c9241', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 08:22:40'),
('tou62990cd5653c2a70eace2dc73af5b8bd', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:49:03'),
('tou62ff0c635b3e6afd55806742b7dc5c30', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:43'),
('tou6339cac4758207baadc56cbbd24ae312', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-18 03:53:43'),
('tou6339f255d1cbd400454508ff66073b37', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-03 21:11:40'),
('tou634e123c6da9a9f6374e2a03e6de68f3', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-08 07:10:44'),
('tou6385d6f092a37cb67613b27d9d91b242', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:39:33'),
('tou63893174c9c7b8c16fe237012528e245', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:16:08'),
('tou63a8d8fb77d375ade17ad67e21a32917', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-30 03:55:47'),
('tou63fc778a14258f3df59d6992a0b6d94e', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:10:12'),
('tou6469414d0de43e8cf4254e90431405c0', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:11:04'),
('tou647ec52e69fd528eadcad728fa1c8ea5', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:31:29'),
('tou6490cbb0b23bd6c8f42d7e7850d6e1fa', 'itm_40c6367fee11bc95a223027f129e2bee', 'usr47d6cafdbced2e9935d62d18d2952cc7', '2019-10-03 08:06:48'),
('tou64d42e56f8904e24d89016f16b0f788b', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:53:10'),
('tou64dc467def9a09e58a359ef779f294f3', 'itm_f1864130c55178f6c61cd76350426c40', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:08:49'),
('tou64ef6d5c13d2d9cb07882016e2e0c394', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:44:19'),
('tou652e358117824a9d4be323978e7946c4', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-19 20:35:23'),
('tou652ecb2305a6a12d7583f591f467e99d', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:08:42'),
('tou6551a053b418b08024f2acc7ad337bf7', 'itm_709970eef73e104ca5d4114b2485355d', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-08-30 06:10:15'),
('tou65835369cc6a8fbb6b41ea8bb40143fa', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:59:07'),
('tou658bf6098e7cc068826ccb746354f90e', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-08-30 04:51:50'),
('tou658d4fbc0c46c46b0ca0be98f80eca1c', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:08:15'),
('tou65c07714354f46074f74614f6ab6d04a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:09:38'),
('tou65ca14178ae7a883c4c5c3eaf53994ff', 'itm_3bc2dd9f6f7dbfcefb5238ae4a6ddf2c', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:56:02'),
('tou65ca39d07acb0b971197d23aa94bbca6', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:46:54'),
('tou65ddf64da58c568f4839f888bd122108', 'itm_81e1b4c39551a5765743662e1f5f079b', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:45:42'),
('tou65f0f1f8efc3f38e6c0ecf6ab1eec8fb', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 12:20:00'),
('tou662f64256a07b5a6e8e10c7470f35fbf', 'itm_715fee0a325de85667e2271e03d8c30d', 'usr3b55803c69a101731a444a70fdf52881', '2019-10-05 03:39:13'),
('tou66614ba66e176a8f75b93ddea85abf4a', 'itm_52c74ad65651f004b6145559b25db4d5', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 11:45:11'),
('tou6669b6736d8271e3e0aa836450fdf906', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:17:40'),
('tou666c9db654bb020e6102016b9fc0d9f5', 'itm_b18cca37df85a01418739145d4369153', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 09:45:06'),
('tou667b786a427aecb1a378e1de6c4c033e', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-03 09:31:18'),
('tou669ae2aedbd3d91fa47e7f089ab70925', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 21:18:04'),
('tou66e3a2260b76694b197cf3dc9117b3d2', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-10-06 06:54:51'),
('tou66ed833f6c769bd032aea3d2002c760c', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:45:57'),
('tou66f3efe791c6f1a1d07f827f4916601b', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:05:22'),
('tou670ddf8871249d8c8eecbce92b0be32d', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:37:13'),
('tou67155f9452c67ca6c436ca1432dcee21', 'itm_aec300faa4929e3342679e55e63c24bc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 04:42:19'),
('tou672685422dce7c5fc3a29c74d48a8648', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'nologinuser', '2019-09-11 16:58:48'),
('tou6741102e0577f37cbe8fcdf5c8d9dc24', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:37:24'),
('tou67496e86548e9f64d9c401305c99b495', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 05:18:56'),
('tou67621eac6a27afe312524b9e60182b9d', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 11:16:16'),
('tou6768d5234f203023a57554d5a65836cc', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usrba287c1864e60a6def65e8fe3242d906', '2019-09-11 05:18:05'),
('tou678fe8820e39b7fa68b2b90a6bc97d5e', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'nologinuser', '2019-09-25 23:14:10'),
('tou67a923101de92318bbc03c9e7c885fbc', 'itm_caf91ce095ca25085c5f89a45a7cdd65', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 06:01:10'),
('tou67b2819d4fb20debfbdc971f66818ca8', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-19 19:08:12'),
('tou67c4e7d02878e26fe8e6e253b0fc2f71', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 07:27:26'),
('tou67eaf9a200c4ffc19af299d2d4521b32', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:13:37'),
('tou67f1f07ce62ba6358b23d4d18c50618e', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 11:50:48'),
('tou67f5775e2620f869e3ecf60e1890abf7', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:09:34'),
('tou67fe14c11d4d36f86ca85c2c54804801', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:10:14'),
('tou681a2185b30ab8c4882ca64f6de78cd5', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 11:00:56'),
('tou682bf7c7bfa9cc08a28a948fdbbc29aa', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:31:13'),
('tou683582a829c245db221882106d914589', 'itm_02db30c4e6a968ac1955cba086d95b17', 'nologinuser', '2019-09-08 07:48:29'),
('tou6842a0cf2045288e4cfb50b9c0c8d4e6', 'itm_b5372f02c59649e04d0639862f151119', 'nologinuser', '2019-06-24 04:34:03'),
('tou6852f05af7d7e17644a3c2633cf34989', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 07:38:19'),
('tou685d31975eb7b01849e7fdb502c51313', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:38:29'),
('tou685df7be75d22b90c3961ab8f3a36ef7', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 07:18:53'),
('tou68775db7215404c99259428d44fd126f', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:12:59'),
('tou687ce2dc68ba26ed580e2184139cd21d', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'nologinuser', '2019-07-01 06:20:15'),
('tou687f36d8e3d73c2e648f597f7f0ea963', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:11:55'),
('tou68aa893eb19c4148737460943651bfc8', 'itm_1a7f3815a51c3c3888ede85bbacb9b88', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 07:09:24'),
('tou68b283435452df2ad1d4e1948eaf6c04', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-14 09:11:08'),
('tou68bcb75556d792bcf337217f7aea8dde', 'itm_b5372f02c59649e04d0639862f151119', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:10:10'),
('tou690cfa5cd4f520b99842269bb02ef084', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:20:59'),
('tou69388547b042c07a5911e38d2d4fab2e', 'itm_2bb3fb13e1040cae112485657e31f868', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 05:25:29'),
('tou693c79795e32fad48981ee8553cd8ff8', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 07:19:14'),
('tou69400eda3f60cf7b79e5c711e52f6800', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-23 16:24:31'),
('tou69485715b61fe1c7aa21ce167f523f54', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:38'),
('tou696086dad192b7aac0cf0fade776539f', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 12:04:15'),
('tou698a29490054578c33230ee82f5df79d', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-11 20:54:12'),
('tou69af52695d4cd579bac6c4f78855b7d4', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 06:47:57'),
('tou69d741a10dcd36a725625f013b5a2bcd', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-29 18:44:00'),
('tou69e0731f26e5c503617a74b141600d7b', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:53:40'),
('tou69e6a806f4af9d249eacc6bff55d711d', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:26'),
('tou69f49dddc1aec549cb47f0c3eac7297d', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 11:57:04'),
('tou69f9270e6962e1822c9d8b29b06fdbb2', 'itm_02db30c4e6a968ac1955cba086d95b17', 'nologinuser', '2019-10-04 10:11:38'),
('tou6a0e3833ea1508baa84e9da2f5bcdf38', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-29 11:53:00'),
('tou6a1ce58c71e0bc34729baaf30007bd54', 'itm_3574960a0f206a81307cfea2804acbed', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 04:22:47'),
('tou6a1f3cd612a2998a8b38a5d4143be6c9', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:35:20'),
('tou6a40d608fd6e501ce39075e7d1d0e873', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:35'),
('tou6a79fa45ea301671df12be43ef581d44', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:19:50'),
('tou6a832bd5b6b2af38e8051252819e7d31', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:19:39'),
('tou6a83489ff9b5eb55b7600f1ac7b214cc', 'itm_02db30c4e6a968ac1955cba086d95b17', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 22:13:29'),
('tou6a90c06882caa1237971d1746d3842ce', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr812a65884b5bff6775cba514ce8288ed', '2019-10-02 08:05:08'),
('tou6ab4a424341806be1cfd242341e7af46', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 15:27:19'),
('tou6abe939358c71453a42151f64ec3986f', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:32:28'),
('tou6addffbf5fa25bf378ad1e05ef8acbe6', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'nologinuser', '2019-08-29 18:43:33'),
('tou6adfabaae380e3aa0c4ebc1339af7170', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:37:19'),
('tou6af5c2663056b814f0ca808beb789a65', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:35:04'),
('tou6afdebe26e3a9d8dd63a5737749e4cb2', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:53:14'),
('tou6b031e8830a39fa281f7e95ec9c09266', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 08:26:49'),
('tou6b39da1da7aa3677addac2d2383784ee', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usrbad733f0fa26f94bc0c072a39d369a8f', '2019-09-22 00:45:18'),
('tou6b4061f082eb12c8637744835231b3f5', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 15:10:04'),
('tou6b4eb17c1760306b45d647a7791f3f4a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-08 14:03:34'),
('tou6b5e58634bb3d962dc123a5b881a5212', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 11:22:15'),
('tou6b6408a16daa89d4ceb319416aa38214', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:55:49'),
('tou6b9e36241879abe066b333ceb236b35a', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-10 20:52:20'),
('tou6b9e490316737dc21f4634adbc32447c', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 08:25:36'),
('tou6ba0f63cda67e08d3d8d70bab9cb9b61', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 06:59:32'),
('tou6bb0652ad88fc51adc74d9d6d51d1420', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-08 07:55:04'),
('tou6c09202a6624a422d674df04848cd1e6', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 07:04:39'),
('tou6c340ea9161d48c77ea15a07fbe234f5', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-09-20 18:30:15'),
('tou6c3590643d316445391d85ed96739a66', 'itm_3574960a0f206a81307cfea2804acbed', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:40:00'),
('tou6ca3e2f86bbdc0ae0619e8939ecd05ee', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 10:35:24'),
('tou6ccf00236eb22c8f2d8f6654802a7252', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-28 12:09:43'),
('tou6cf4403c23c87e36eb6e8b60e7b21a4f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:38:41'),
('tou6d0f1da0da8718efea25e589fe79ad56', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:05:22'),
('tou6d2e6303e36f58c7937dc8162803dfd2', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 06:06:02'),
('tou6d4d42b0c1306677435b233ca3f4668b', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:12:56'),
('tou6d55c689f74a86b2fe6766bc0fa3e22c', 'itm_40c6367fee11bc95a223027f129e2bee', 'usr47d6cafdbced2e9935d62d18d2952cc7', '2019-10-03 08:02:49'),
('tou6d6a77465f38ad53e67c74380b608c54', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:12:22'),
('tou6d6f65490cb6f65e92d0868ddda1b1a4', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:55:22'),
('tou6d7ebc83db8c5fc9d362e1c62dfc0420', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'nologinuser', '2019-09-17 04:51:12'),
('tou6d9e04da47f02bfc40636348b9f00a37', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-09-02 10:27:01'),
('tou6da69acfda173cb782dc5855da4c66ab', 'itm_82fc8ec356881e51c9a88e15ae38f339', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 03:29:57'),
('tou6dad2e0fc83ad64a1696abae188c4c5c', 'itm_81e1b4c39551a5765743662e1f5f079b', 'nologinuser', '2019-09-02 08:43:07'),
('tou6db089bf7c3126fe5525f7097eaee3ac', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:53:51'),
('tou6dc6bbe679006d174c4b563a1db82d91', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'nologinuser', '2019-10-03 07:52:39'),
('tou6de9ba876ff2c47dbb6949618b40bed6', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-18 11:29:32'),
('tou6df0d702c0c34b2e621219d68a04bda6', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:38:38'),
('tou6e175851fa858da61a7b9e471a58dd22', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 10:14:05'),
('tou6e18688f1d3f8dc44e47f3d266f39a8a', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:30:59'),
('tou6e1990d57849162bfd89621a779c6d5c', 'itm_ee73165b7c94f2eff3a4201b9fe73c6b', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:15:37'),
('tou6e326fa4a92555d23e4173adbcc515c7', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:31:05'),
('tou6e411a03072a9f19a36e8883c6ec1ac2', 'itm_3574960a0f206a81307cfea2804acbed', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 12:10:36'),
('tou6e47318869c5744f1181b46d5676c920', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 06:37:39'),
('tou6e73440b2817bdd789bea2f9928ba6df', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:58'),
('tou6e88a4dd09dab0bc78ac55a8b07e6ff6', 'itm_5058fb44b1f3a2efd9cbd0925ab67785', 'usrc58fe4f5fea11c19ee38cc1de75949ea', '2019-10-07 07:31:21'),
('tou6e8cf5bfce226f7e64786bb37dd7d388', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-18 04:06:09'),
('tou6e951bb002cd77aeb9723adc07dd9bf9', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:53:18'),
('tou6eb1692e2e3f675de1bf5acb132947b9', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:44:49'),
('tou6eb4a59fef446618a6798d570f12d704', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-08-29 09:58:55'),
('tou6ec77b522fdb41a11690b7bf723956ab', 'itm_7359de3d150f84bd9e06d9e6054ba151', 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 07:40:52'),
('tou6ed1645f57e90a63ebafac9c77f9b0fe', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:41'),
('tou6f12bae39c1ee42d92af05203b5e45aa', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:03:40'),
('tou6f17ace077adfa428e5d6e4ebabf675c', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:04'),
('tou6f35416a83826ad7ce97a2070baa4200', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 09:59:31'),
('tou6f52e6a2471f29475948af8e1804b8b4', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:47'),
('tou6f572430dea06fed57343c8e1dd5b02b', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:53:52'),
('tou6f5881d1518685e55cf9b95db0fc1793', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 08:19:19'),
('tou6f5f28c5e93e1d3ec772e2aec168b90d', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-24 04:53:27'),
('tou6f7084502f530f23001f6ea478d41b24', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 07:18:56'),
('tou6f71c860e422bc402087586fc84207bf', 'itm_a88e7b941c622da8ea94af30f6147997', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-11 08:41:04'),
('tou6f95ae45c11d856e21b05e8b662d4e24', 'itm_8277fd5c824ab26ec25f902c7026a40c', 'usrbbb81d64f7d56bdc2d7c0dff91bd2346', '2019-10-07 07:06:49'),
('tou6f979a64eda0ad647edd69db7d48b734', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:53:21'),
('tou6fa714812d52f58d7c6dfe6c8b90b683', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-12 16:04:59'),
('tou6fb38b154a6d8655b1816ffc6c0f3408', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-10-04 13:24:13'),
('tou6fc2181a0d11e0d11ceac63068ba9a22', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:53:46'),
('tou6fc508ba2e9ec377d6095318096b022a', 'itm_709970eef73e104ca5d4114b2485355d', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:19:33'),
('tou6fd1c325e06a1d754fa3f0e694f7bcbf', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:25:32'),
('tou6fdda4a434d6578f089e8afd1ad8669b', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 06:12:13'),
('tou6ffc4118867c569066dceb9d65beccf8', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:22:38'),
('tou700a5e55ca2d68660f1385eda97fcb81', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-30 04:08:55'),
('tou70117a0aa5d01cde0b948fa4ed265a30', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 08:11:14'),
('tou70224b1352e4f8503a5260e18fafb7e4', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 03:30:07'),
('tou702adb59e1e7a0fd613677ae4db08248', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr8f466efaba2c555c63f6b739db4f6f6d', '2019-10-07 03:59:02'),
('tou703d5372ed32d75da1b95f37038cfc4a', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:14:48'),
('tou7068e012541d97d28bd5bc0162693d63', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:55:38'),
('tou706e6eb0fbba7a4ffbc309c665b2d43a', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-08-29 18:44:09'),
('tou70c5d3e755d5a13b6187df0400da4479', 'itm_2bb3fb13e1040cae112485657e31f868', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 05:10:06'),
('tou7105fec92c010bcd01163d3b6d795964', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:31:18'),
('tou710eb6a7c45e846194d232e3375c8768', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'nologinuser', '2019-10-02 13:57:37'),
('tou711a466cb147713f9f46e930de8e7662', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:38:17'),
('tou7122e1153e2217bb26c0283bc966b9ab', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:20:02'),
('tou71444b9327282ef7608a7ad6a0b330d4', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:59:19'),
('tou715dbd8483ff6b3b5e5e97441d7b8106', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:36:06'),
('tou71d3128519bfe069792ceab44b3190ee', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:50:57'),
('tou724fdc916a6b8ae29f74d79d903f7c70', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:33:41'),
('tou727e03b1fc5e72969ec0748ed4d725c3', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 03:52:55'),
('tou7288fa9f6fb7f55ba71e4a9844b97b37', 'itm_d226e2115d5632e9aaaac89bd880737d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:26:57'),
('tou72cf305530697886b8cce91db2cac9f9', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 05:52:49'),
('tou72e01c83e650be51f88e51ee55a234b9', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-08-30 05:40:04'),
('tou72e043326acf7e4953f31449f798d76d', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-08-29 14:38:24'),
('tou72fa1234d6d15203d7b06d7e8961c2ed', 'itm_715fee0a325de85667e2271e03d8c30d', 'usr786aa1c24816cf370ecd666472d8b548', '2019-10-15 18:11:15'),
('tou7311ddee2fc8367ace6e1bd510ba77af', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 09:59:20'),
('tou734771d996d83fc0efa65e3be01e186f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr8f466efaba2c555c63f6b739db4f6f6d', '2019-10-04 09:07:49'),
('tou734ae6d033f7acf404e20324eaafb218', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-12 18:25:20'),
('tou734c674132fb570d546e130d7ca3ffec', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 11:38:22'),
('tou735b327c153c04a4c0180c6927e827c7', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:22:13'),
('tou7374c5d311f95de13401fc2cfe2e6c70', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'nologinuser', '2019-07-02 09:51:36'),
('tou7387a8107c15e767ddd87eff2d25867a', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'nologinuser', '2019-06-22 09:09:48'),
('tou73ad07ede6982810b6338e4b27c19ae7', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:48:56'),
('tou73b0347a60a4dc68a457c6bf0109e408', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:23:19'),
('tou73d025e94bf00fbbb952d004baf2cb5a', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:50:47'),
('tou747a69e13278d4812f4ecb49cd0901cd', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 11:36:04'),
('tou748c8cba8b79aebc34220189b45835cc', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:29:49'),
('tou74afaa95daccdfe9383abd3378bb2c07', 'itm_45f53a2e646e484d6037e562f4233cfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 00:29:42'),
('tou74de15750ae4a068efda7a64cbd5b436', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:09:55'),
('tou74e0d0232801de370385c4bfc09cdabd', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:54'),
('tou7567644706e69ab6f30fd8a4253f0cc6', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:43:29'),
('tou75699c2ccb85dc72c4f5bd0594e7a1fb', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 06:59:36'),
('tou757bd081840e9b2b7d388ae1271614eb', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 12:20:13'),
('tou75b7e017ee71696e6ea9577563f48779', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'nologinuser', '2019-09-27 22:05:37'),
('tou75d83663d2a5d4b036f875a6df9ebe14', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:18:05'),
('tou7633e07d84143e5a77f55d8ca7081e4c', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:35'),
('tou76538d55d91ad76f7211b60053787f4e', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:39:41'),
('tou765b55f2d7951a54360c9bad92222a95', 'itm_941519b2438b0747386a6fee39e5b279', 'nologinuser', '2019-06-28 11:09:02'),
('tou7663f8d88c71564e438cdbe66bbcaeda', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-13 05:46:48'),
('tou769368969c58b0ef21f969aac154268c', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:03:56'),
('tou7695023d1a53e3456f56d80ff656d417', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:30:22'),
('tou76cdb4a054767e6b2a8a9a880f0143c7', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:36:47'),
('tou77033872d16b5dc563d145593c4ac48a', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:40'),
('tou77222f206c1c21356f2cac0c58ca91a6', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:49:47'),
('tou772a4de01653e8b048be7a476f3ff1cd', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 06:12:09'),
('tou773550c84edc0ec2e36b46293215ef3e', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:30:34'),
('tou775761f477b107bb556d25cc27d66bed', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:09:20'),
('tou779034677b22fad88ea11bf9258fe8fe', 'itm_e484812d33f6ca0a25e3143683d67688', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:27:55'),
('tou77aec4ac1922629cc89f887f28217877', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-07 22:54:53'),
('tou77c907ee4062297f148f720846562949', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:54:15'),
('tou77c9afd941acd934721f7a0b7744336f', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:35:30'),
('tou77ef17765129d0d5ca215324a6da15bb', 'itm_2bb3fb13e1040cae112485657e31f868', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 05:25:29'),
('tou77f06e5b40a3a7cb462a21a724210af8', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:11:20'),
('tou77f841bab5a8f47b7e5303e69b261c7c', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'nologinuser', '2019-09-05 14:58:25'),
('tou77f89aadf3a1116edb68c582ed40deb9', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-12 16:05:59'),
('tou78443e81cc874848db9f47153c4de1c2', 'itm_709970eef73e104ca5d4114b2485355d', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:33:14'),
('tou785a8ac3a01e272c7fb2fe1599f68c86', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-08-28 13:45:29'),
('tou785abe8f26815c055e0e23d6c6744780', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-08 07:55:22'),
('tou78679c42dfd481eb0284ffb25f5620fb', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:18'),
('tou78c7c8031927abcc5ba6315ee5886889', 'itm_19d8a632ca279d2a4739913172cb71dd', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 18:46:26'),
('tou78e1a11740e792efb5590a0b43fa0acc', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'nologinuser', '2019-07-01 08:00:24'),
('tou794e1df5905504412869b4574a071398', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:32:20'),
('tou797b87d14e729918ec05ac5594de09f8', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 10:19:19'),
('tou79add84442a8819b55bf9b1c03d6882e', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:23:50'),
('tou79b415442d6fcb1ea8192725ac8224b4', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 06:10:41'),
('tou79cd4713a206a4b0aa4868719b04d6c5', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:09:03'),
('tou79e6ad687e318b647401fbc75f1101b7', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:19:43'),
('tou79f1e87fba8f412733f38574276f5af0', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-01 23:09:52'),
('tou79f50790ca0ac096385327b9baee9240', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:39:36'),
('tou7a195d4d1263b73a9cdd5bb32fdb0b20', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 11:09:14'),
('tou7a285f3532202b2dded957c734fb5ffe', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-04 05:08:29'),
('tou7a6bc9dc32ff6b066109364efbff9050', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 10:19:28'),
('tou7a75ac8b6e243987281ca8ba11530472', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'nologinuser', '2019-09-08 19:27:21'),
('tou7a78c454f55d8cb5a941f6c44f203dc5', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:19:58'),
('tou7a7990d1806e89a06070c4b706651601', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 10:30:10'),
('tou7a8f4afcbdc614905a60a9a3c36cdc3e', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'nologinuser', '2019-09-10 21:15:22'),
('tou7aadb5ac76786a747e4f09906f55951c', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:14:02'),
('tou7ac2075770812c799a02f808acfab1b9', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 03:52:55'),
('tou7ae27892792a8c091f8123e1183e8b25', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 11:02:22'),
('tou7af8e46b2a493a85569c009f57dd8cb4', 'itm_709970eef73e104ca5d4114b2485355d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:53:48'),
('tou7b1b3ae4bdb03b03fd22678687db6d1f', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:51:47'),
('tou7b1b4b9b2074ef05b0644caa957db9ee', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr812a65884b5bff6775cba514ce8288ed', '2019-10-02 08:05:05'),
('tou7bb34dfb20befb884b69657fc48b5521', 'itm_aec300faa4929e3342679e55e63c24bc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-07 03:38:54'),
('tou7bbd5b2a48cfba5213be63a1a42890ef', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:01:57'),
('tou7bc12153ac62894ba89b2a06c9a68254', 'itm_941519b2438b0747386a6fee39e5b279', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-02 11:58:22'),
('tou7bf36dcf1aeae723be1b39012007f3b0', 'itm_45f53a2e646e484d6037e562f4233cfa', 'nologinuser', '2019-09-10 21:10:31'),
('tou7c300e7d60883e1bc69f1e8e52e6dd81', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:39:03'),
('tou7c3514ff540d62f483e08cc8a80c97e8', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:36:57'),
('tou7c3e11445f6096dc570f09930ea3fe43', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:03:38'),
('tou7c42d1ee35a5d0d6906e0f507e6485a9', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:33:34'),
('tou7c5b57e27c452b2a72bf9ef7dc80412e', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 07:02:41'),
('tou7c7fe42c5ee8e2f59f3346406d16a69a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:25:19'),
('tou7c84e53fc2149253db37dd559785ef29', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:09:04'),
('tou7c9c7de9a8c1586c9d5c1def4c8c293c', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:44'),
('tou7cb489f4dd988fb22ea94d40b440d69e', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-10-18 04:15:20'),
('tou7cd2d4542a7876fc4e83dfc28c6870f8', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 09:49:02'),
('tou7d0b68ffb1b1d799e14f1731d01bcb09', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:43:16'),
('tou7d2da13ce47f68185fb2154581aa8206', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:01:57'),
('tou7d36207829142bb3704e61b0c149f2bd', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 09:08:44'),
('tou7d3ef7c9c4ca8c31421c350f6c350c1c', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:12:59'),
('tou7d3f645edb742be37f7dd09f4db039f8', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:17:09'),
('tou7d51c19d6dd97661eedfdbcf1108f94a', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-18 04:06:37'),
('tou7d646b4f0982ded24fc57d74673566da', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:29:13'),
('tou7d7128f45c30155985ec440e6ab23f5a', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'nologinuser', '2019-09-18 03:52:35'),
('tou7d93656ac8a2d57ee756b2ca4cb1a7bb', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 08:42:32'),
('tou7d9c4f7037284c270f56a11b74a4dbb8', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 06:31:49');
INSERT INTO `bs_touches` (`touch_id`, `item_id`, `user_id`, `added_date`) VALUES
('tou7dc4f8e2353f65918bb3a945d6676c1f', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 10:19:16'),
('tou7dc59ef166bd43dd6457a38071ca059f', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:32:56'),
('tou7e06f1b8e20084b9a525390abd0a6b1e', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'nologinuser', '2019-06-22 09:09:30'),
('tou7e7a54d6e9ba139a90047e05ec874c1a', 'itm_3a197e7a07668e4d8580e5b129957107', 'nologinuser', '2019-10-13 17:17:11'),
('tou7e83f4ed8a1929e1f5de636dfed0b823', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-30 21:17:07'),
('tou7eaa69a8b00eac26066546ea83b0cf79', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'nologinuser', '2019-10-04 10:17:02'),
('tou7ee3de9d1e4f2709c2aa374d580de140', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 03:36:26'),
('tou7f3532717ea105ac12438f2dccbf0709', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:36:40'),
('tou7f412030c2bb8ca1fc7a829ac4c56359', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 09:46:13'),
('tou7f78b4e9efe17744aa44025348f89261', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:43:18'),
('tou7fa5c7adf38e3c159cc6863dcefb61ed', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 10:44:48'),
('tou7fa7fbd2ce0bd92270925fbc02855d42', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:58'),
('tou8018dac5c9b0718622a2674e9ce4a499', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr8f466efaba2c555c63f6b739db4f6f6d', '2019-10-04 09:07:49'),
('tou804dbd431cbd72bb24ea1bd7b869a372', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:21:43'),
('tou8073767fcafffdb6fe4a7fa280b94f9e', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'nologinuser', '2019-10-03 07:52:43'),
('tou807dbaa9080fe15ee7b7d37253bf1e6c', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:18:46'),
('tou809d12f9b2eda1c9e1d5156889c5bfc6', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:27:22'),
('tou80a34f8706fdf8b171c86f0d13765271', 'itm_1b53dd85ee66f058795ee2971ad55fc8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:19:07'),
('tou80af749c5a432d572625a13c4d813d70', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 06:46:42'),
('tou80df1c4cc0f737b55ca8c7eefc7909a9', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:20:26'),
('tou80dfa49502bfcd57fdd6b56f4c127adf', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 08:12:13'),
('tou80e05ea5bfa2de045cbbc72f9ba27c77', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 15:27:16'),
('tou8132f948743667c60c1bce236e9d3481', 'itm_3574960a0f206a81307cfea2804acbed', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:39:18'),
('tou813f2de6f93e0700c9b1dd5448300e64', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 05:16:20'),
('tou815e6fb0629440d4b87ada077ce184b1', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:34:25'),
('tou81671fd3554da13708963570017eeab5', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 22:06:48'),
('tou8188027ab556fe748ed67d09c450149c', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:08:42'),
('tou81c7edfafb86f172484ba8505452b299', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:23:37'),
('tou81ce1079d480592390a1fb1e7941eea0', 'itm_a65be83fb825406c14760f8848ff7bfa', 'nologinuser', '2019-07-01 07:56:12'),
('tou81d62acddcdb182577c2ad65b6d12a24', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 04:10:04'),
('tou81d6f9cef5f011c1b7a5bdb15b0dbfdb', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:05:48'),
('tou81db2844fd9469c93428204380ad3d2b', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-02 11:26:06'),
('tou81fdc28b73f639a20411adc72ea79f03', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:49:40'),
('tou8226dbd83c9f189abcbcb804788e5a40', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:30:18'),
('tou8233e6f78a17bdc9a23aa976e652bb64', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-01 07:12:56'),
('tou826d6b0ecbf09522138a94abd94c4883', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:37'),
('tou827626f795e4abc5634448aa006d2fc0', 'itm_10c058734f4608bb3b1b2c3b8a7853cb', 'nologinuser', '2019-10-05 05:12:27'),
('tou82989cdf4e6977d2602d8872ad563080', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:23'),
('tou829eb1ac90cb2cee0f17c564830bdd28', 'itm_10c058734f4608bb3b1b2c3b8a7853cb', 'nologinuser', '2019-10-06 11:57:29'),
('tou82cf6788a8f87fc5063e08e3c4696c46', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 03:47:14'),
('tou8316fac5dc5cdfc0f421b578c09628ee', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'nologinuser', '2019-10-04 10:17:04'),
('tou8338760dbce582c9f8e5bc2a8c180798', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:02'),
('tou83443e094f1d6c377e3b009dded7e6ec', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr3ef9bac5ec67dac879f099d0faf16d84', '2019-10-13 09:23:08'),
('tou839c850d9bc072f0b166c3eda21cadec', 'itm_45f53a2e646e484d6037e562f4233cfa', 'nologinuser', '2019-09-10 21:10:31'),
('tou83ad68ae2d5ceed35b629bc3dd5688f4', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 16:23:55'),
('tou83b876a73ce039e60aa05691267b2564', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-04 03:23:24'),
('tou83bd5f414b478c1b147fef9519586d06', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-07 15:00:20'),
('tou83cec6c59bce363e625d5ea1acd208cf', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 05:43:20'),
('tou8404465cd5a96fb4da6ec9cc5acf455c', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 09:43:26'),
('tou8414f3add4d89ae76fe6d19d5b34f53d', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 06:01:51'),
('tou843128b02e37df34bf911e7aaa892bb7', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:24'),
('tou845de71d681af0c2d27a084a9a83c4bd', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:44:28'),
('tou845e24c41bc0f2bcfef6720f4b7ae470', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:02:11'),
('tou847aae5067a3216aca6d5cc18e330d19', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:46:31'),
('tou8494bc185fb3fd91fe0bb586b9705668', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-24 04:54:25'),
('tou84aad91c32cc7f7bdd6611f8317f678f', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-07-01 03:30:24'),
('tou84c5fe82ae9bea9a0d1e772c4e672460', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 18:45:28'),
('tou84ee2cec5c5748aa058d29336dd9a4bb', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:10:20'),
('tou84fab754c3198e4d891f424e0bbcd13d', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:30'),
('tou851ed37408e7301a4b0524cc0dfcbbd8', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 07:28:38'),
('tou857634ff88e9ea2ea8edaf37a786c604', 'itm_52c74ad65651f004b6145559b25db4d5', 'nologinuser', '2019-10-05 10:31:41'),
('tou85b2f9fb945ee44206e4d9701121bf51', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-10 21:14:26'),
('tou85c528964e824b441b1edf8a34933852', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:14:54'),
('tou85e82843ab4d1f34c508bba68869a883', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-08 07:20:12'),
('tou85fca7c1571afb21d4f3c341967602ea', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:33:26'),
('tou860121db610975ab139ada47821a6688', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:24:06'),
('tou861f3e50ae3bd29897e54b3259d1fb42', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 15:27:24'),
('tou862c33b5680eb94093db9541971cfb10', 'itm_0580d078a65d3887457b4941033ce226', 'nologinuser', '2019-09-16 15:28:07'),
('tou865084ef0b0db95a56dcbd61476581ce', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 23:28:00'),
('tou86642e8fa5e111e42887274c7654d97e', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 05:43:06'),
('tou86bae7a3f80b6584652950653292f0a0', 'itm_0500dcce80c7d70b79453a8ff4096428', 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 08:03:44'),
('tou86bbe721c32e267af5b41e1fd3675e2a', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:14:50'),
('tou86cf81b1515dee940ce474d9a7f651c8', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:41:53'),
('tou8729b5b976ede895d4218d885ffa8cbd', 'itm_709970eef73e104ca5d4114b2485355d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:53:40'),
('tou8774652c2aecec3e729d56f5367bcec9', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:25:29'),
('tou87a8788b35eede85c2c76240399a7dea', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:03:18'),
('tou87b3bb782315f0f61014f405cfbcfa6b', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:14:18'),
('tou87cf5ed44c9016660adacb1a441336d0', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:31:00'),
('tou87dc0b6b4a52519211ab49095f1da8ac', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-20 20:06:26'),
('tou87e8531d516173a66b667b5228703301', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 06:39:09'),
('tou87eaa11a7f0b84a83af43fabda447577', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 12:17:00'),
('tou87fabdffa97343dc94bda75f0057c8a2', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:05:03'),
('tou88029a3e1e3ca89012e73f56360959db', 'itm_e9584f6420da26e2378adffc455f7431', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-18 10:47:19'),
('tou88240b62a33493584ec24a03659ed1ad', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:25:16'),
('tou8829d6a9449e9f72479500d1f9f0852f', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:18:57'),
('tou882a33200a77ba62883c0860604719e1', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:25:22'),
('tou886037577f204a33ba8b6f7ba8f6be3b', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:08:06'),
('tou88637f3d7ac070ee38b460068a262c61', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-08 07:20:12'),
('tou887c60017fbffbb1a06c52ddc5d4f097', 'itm_aec300faa4929e3342679e55e63c24bc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:26:31'),
('tou888d5dbde314d3574770b1dd9b680c3c', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:39:51'),
('tou88a769a820413309f84e916793641dae', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-14 20:36:56'),
('tou88c0d0e8f56484e3e3ab8b9d2a4ab375', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:46:25'),
('tou88ca48380d96a62a57be63f18b656362', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-10 20:50:08'),
('tou88ccee60117b9ac140a1f5599a645d14', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:28:44'),
('tou88cdbac9a4bb38f978937a30b699efb9', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:43'),
('tou890945a46c641892c5dffde08eabe1d1', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:20:31'),
('tou891ebd1a4b717a3472007eb5102cb143', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-08 07:07:43'),
('tou891fc6ce1eef58896745cd9cc55e23ca', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'nologinuser', '2019-10-01 10:31:01'),
('tou893e2c47cbb5680a4f60f924847efbe7', 'itm_52c74ad65651f004b6145559b25db4d5', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 11:45:11'),
('tou8946d75cbffd4ca6c3b498cfd9ef73b2', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-20 17:28:24'),
('tou895d9034f8d76445fc44f383ce4a200c', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'nologinuser', '2019-09-20 10:35:38'),
('tou895f8425843b22cd12f32495b04202f1', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:39:57'),
('tou8963750aeaa7c387fe1e24db108ea176', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-04 08:19:58'),
('tou897e501e114aa71c70dc42302ab8028e', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-03 09:30:07'),
('tou8992b4e902816eb14e2a82bad9059bdf', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:22:09'),
('tou89970089181839737241f915e5315f77', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:48:31'),
('tou89b5333af91660856b243637a26a0928', 'itm_709970eef73e104ca5d4114b2485355d', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:32:33'),
('tou89b6a04d328b8d9a076b13d535df124a', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:00'),
('tou89bc60e0ea9ae5cdbbc9c7401961a14c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 08:08:14'),
('tou89c9dae8fb3b0ad19a8b9559ac93ada5', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:44:55'),
('tou89e21a80878d5ccb89c05784837aa3dd', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-16 04:44:02'),
('tou89ead87dd1cf48778769b8480b679486', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:33:15'),
('tou89ebaac60a756c7cd646e4dabb1412da', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 09:49:47'),
('tou8a36443cd6ddcb7b720886b9ba6ddd62', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:16:47'),
('tou8a41c97a8ed4cd699f0e051ab86f42b2', 'itm_5445c989cdc31269853337a5c8bccf7c', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 10:03:08'),
('tou8a41f5b9292550a5f409cc663ad1a38e', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:21'),
('tou8a52569c71678b0516cfcae4c13806a8', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:37:01'),
('tou8a66208b6c58b2151f8c4696bd7a8a83', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-18 12:10:17'),
('tou8aa248f6473e12a645258a676ba3d3e7', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:49:19'),
('tou8aa9a5366d261b483cb530977a82718d', 'itm_0580d078a65d3887457b4941033ce226', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 13:26:10'),
('tou8aada9db56c9ffe241a850b6b20df11e', 'itm_478864abde0d4c253f54cc5eb3a68619', 'usr1db69cc9b7c316666af1bb9d623e031a', '2019-10-07 07:49:28'),
('tou8b09a9e303ea97de7dac8ee59dad7c56', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:22:34'),
('tou8b2d59d1e9b1452797d16ddcc20a9622', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 11:35:35'),
('tou8b417bdbbf96799166fdf1b2f0f7fb92', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:01:41'),
('tou8b830360ad00da1d33f16d3403b361d6', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:22:58'),
('tou8baab163ad0b9ec05f3d44d3dd03017b', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 12:06:46'),
('tou8bd9ec71f5242cb5aa4929fabed393d5', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-22 12:33:14'),
('tou8be27c1d4a9bed179125c3c2ee357ac1', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:52'),
('tou8bf5b55b1d5072a1774d061946683528', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-08 07:19:34'),
('tou8c100ba4ac4edce9c8c852bf530aefac', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'nologinuser', '2019-07-01 06:20:52'),
('tou8c33cc6abc91472bfb1409a3f38d1bae', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-14 09:11:00'),
('tou8c4dc6ce38e568b6c0e38883220d52e4', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:39:44'),
('tou8c5aab21416e214e19209ff10ac336ca', 'itm_caf91ce095ca25085c5f89a45a7cdd65', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 11:01:00'),
('tou8c7c15901d6f45bdf7eb55271bf17967', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:24:45'),
('tou8c90d106e662f8e9437b1d425d180a8b', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:57'),
('tou8cffafab362560098b44eb090549b334', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 04:20:37'),
('tou8d02af150ce362a326321055e30d4ac9', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 10:43:37'),
('tou8d194c689195a33344047ab31b6a5915', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:43:31'),
('tou8d3d1d392d2c2d22b8f99d57df009c6c', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-26 11:58:52'),
('tou8d40ff712abe6199742c9859c556f29a', 'itm_02db30c4e6a968ac1955cba086d95b17', 'nologinuser', '2019-10-06 06:55:07'),
('tou8d47a7627f7de2fc59518a63c1d0366f', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-20 11:52:16'),
('tou8d4d4dc1f6a00681cf7e5e23464d35c2', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'nologinuser', '2019-09-15 16:06:46'),
('tou8d5b7334263595b458dbd45227c9de99', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:34:24'),
('tou8d8349f99b4e12b3e38006366247b2b8', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:23:53'),
('tou8dbf958533be2b2ca07f68c87499a532', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:27:20'),
('tou8e0c999b52312663d7249ad348bb8d1e', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-25 23:34:48'),
('tou8e1be5206f352766ff1187d1084314b4', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 09:49:15'),
('tou8e5d3aa30632541b04fe500b4d361df5', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:08:48'),
('tou8e5d70869b1388a50d6ef90779a20d00', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-10 22:03:55'),
('tou8e8191d34cf0e7cf3d7dbc9656a9fc9c', 'itm_82fc8ec356881e51c9a88e15ae38f339', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:33:13'),
('tou8ea08e438225aba1564558b0ba51abd2', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 03:20:36'),
('tou8ec2f4db91f5fece0dee623b9feba697', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:35:43'),
('tou8ec5b040f6e65185d94b5d9e2979b485', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:42:04'),
('tou8edf3b61d801f007de61ebb4ff432fbf', 'itm_f84567fffc92857a220e2f1eb89f9971', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:02:52'),
('tou8f00419f1a85ba1ec8687c06bbd8700e', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:49:54'),
('tou8f16872c5a580f67fd8aafbdf938e699', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:14:32'),
('tou8f172c0b715b3b5f1a8c677f3ac86917', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:23:07'),
('tou8f2ab29b6f3ab3d56e7a57c2f8ee82de', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 07:01:22'),
('tou8f2bf7ebb38e53fa874f555954ae3210', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:32:25'),
('tou8f2c5d14b78ce4ca465a644ddff972ba', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 07:28:31'),
('tou8f3612f12be2d972766f5990d2513464', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 08:54:04'),
('tou8f9c337d74cf25cdbb9b4f3845cbf1e9', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:05:15'),
('tou8faf792c065067554b61e352d98b5af8', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-20 17:57:15'),
('tou8fe14803cfa7f7d85a6836aee4137e76', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:22:53'),
('tou8fe6040ab261b7c740a5d897a43897f3', 'itm_0500dcce80c7d70b79453a8ff4096428', 'usr9f42abfc8638a5e733125a12f37bbf2a', '2019-10-07 08:00:09'),
('tou8fe9706f36d47d8c2e9ca89028ed04ca', 'itm_10c058734f4608bb3b1b2c3b8a7853cb', 'nologinuser', '2019-10-17 11:42:21'),
('tou8feb9abd5a3cfc8f1e87dc2c977357b6', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 16:16:08'),
('tou900e8b731818c5e895f17aeb05930a76', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-01 06:17:17'),
('tou901552d64753316763a888a39faccf75', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:23:38'),
('tou90295c08c14833e90a16cb33f47e6d66', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-03 21:11:14'),
('tou903d69bf5391839ae88f586f1a8872e2', 'itm_2bb3fb13e1040cae112485657e31f868', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 14:47:16'),
('tou906d2ed2de31196400f99a852dd8320f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-07 09:57:20'),
('tou9070c662d83e83e0a28fc7a97fa368fa', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:52'),
('tou907b19d3fd27b272ee175343d72a8fa8', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:30:46'),
('tou908d89b4d2f48253e408ea0b7b49d99e', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 09:32:42'),
('tou90b76be925176fee3aa9c9f87dbc2b6f', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-18 22:39:29'),
('tou914000b01ee1d76af39cbeccb41f1434', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:24:55'),
('tou91563bbf18ce98c3631013e3648d01dd', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:38:37'),
('tou916cee2920b327358502ef45007f19fe', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-09-28 10:12:28'),
('tou91bbac1fbe965e6d31f785745aec5d96', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 11:08:48'),
('tou91be1d1bffaece680dbde26aa6ca8eb3', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-09-27 11:45:29'),
('tou91bf2bd209fb4970c5d9ed23c03d060c', 'itm_0580d078a65d3887457b4941033ce226', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:48:19'),
('tou91c773bdb6c915e2c7a8dd0efd4d909c', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:01:16'),
('tou91d58a200a40f5eaf9fcd909cd0e9ebd', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:03:07'),
('tou91d8aa699652e5c84a682f6b92d3dd45', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'nologinuser', '2019-09-13 21:33:14'),
('tou91e99c8d74319a4769cd2729e85bf525', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:15'),
('tou9201b5993b2d5be6922731bd73733f32', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-07 07:39:33'),
('tou92120f4208e8cbf2db8289f837b2d97f', 'itm_b5372f02c59649e04d0639862f151119', 'nologinuser', '2019-06-28 11:09:27'),
('tou921d141988e843d2157c06992b7248da', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:38:40'),
('tou9243d6bdbc5a8feedffe5fb5390bd8a0', 'itm_9ef57934867903960a9849bb7af133e0', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-16 04:41:52'),
('tou9246b8597321b6b0cdaaa66955025bdd', 'itm_d226e2115d5632e9aaaac89bd880737d', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 20:37:25'),
('tou92aa6f37e31e709e003268225ef948c5', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'nologinuser', '2019-09-16 15:28:29'),
('tou92ad0f319f3eca38b8cd25c453c18e72', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:44:59'),
('tou92c760875dbe2b6c725d825ba1118c41', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:28:15'),
('tou92f9c9e9fa8d5d8eedf335ab39e6f832', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'nologinuser', '2019-06-22 09:03:40'),
('tou93251b039eca7919fe37250f5868e081', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 10:43:22'),
('tou93489f36493e27286d6ccdac7c8aeaeb', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'nologinuser', '2019-08-29 23:39:02'),
('tou93684ecdde173ca77255dcebc9f96c85', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:28:35'),
('tou937b8cb5ea34791029e62adb1eb49c0c', 'itm_ebef4d08a7e45bbb87a6ba627f6e1f17', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:41:26'),
('tou93b1f46287ca87fd41f6a455551ee6cb', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 10:11:30'),
('tou93b5a2fe84b31fd7736c0872a01e420b', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-10 20:52:42'),
('tou93fb3ab4a85d9ed1ace9df5dd76e5418', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:32:15'),
('tou93ff07d388cad3b0fb9500e8cad7bb64', 'itm_b4f9fddb9b6f3a6bb0f8baaef7da4967', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 20:37:08'),
('tou94046ec5b328158340b6714f74c323a6', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:16'),
('tou941683cb9a6f2eb6cc7e810b436b13e3', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'nologinuser', '2019-09-18 09:38:57'),
('tou9426e6fc127e038e9d2113525fc2506e', 'itm_7359de3d150f84bd9e06d9e6054ba151', 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 07:40:42'),
('tou9432d1f09427e1e569d2610633d44e34', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr0af0a9e866854e1205b104df27fcb0da', '2019-08-30 08:58:16'),
('tou94536dfef69176ffca030aaf02f36aa3', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:16:50'),
('tou94851305a260039555bb13f0d37e37b6', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:16:33'),
('tou94b984e01b790295c9b497dee5a3011c', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 06:07:48'),
('tou94fb9a550217dcda975a094b196f202f', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 11:01:09'),
('tou952a0075129cba68070a0351cf712989', 'itm_709970eef73e104ca5d4114b2485355d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:53:36'),
('tou95508de527ec2c110890b3c740f11c9a', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:36:41'),
('tou9588dfee15c1651db418278d90536cf5', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 21:13:18'),
('tou95a44197850b434e160f24cb8bf3d4ec', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 15:27:12'),
('tou95aa4ef7af5712d53ed9f374a15bb6db', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 09:11:17'),
('tou95b47d4ec1b391f2e65d8d398bea4403', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 16:23:08'),
('tou95d10aa045b40adb61d34d10e485b6a3', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-11 06:43:59'),
('tou95d1da910b65290c6edbcf6f813ed28e', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:03:50'),
('tou95d7c7954009982e853e6adab079894c', 'itm_0500dcce80c7d70b79453a8ff4096428', 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 07:59:58'),
('tou95ef017530e6aa5f68adf7e9351061f7', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:09:55'),
('tou95ffa4b4cbb6478fddb7089c9b308b52', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:20:14'),
('tou960a8be1492445a4997c6bdc49c4e75c', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:02'),
('tou963b8e648f2a384ae96a6458c0cb49d7', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 11:24:23'),
('tou9656d35339eef98f68e7b1cf1476ca22', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 04:50:00'),
('tou965a0f5569c4f8a2d96a6f9a7571b4ed', 'itm_709970eef73e104ca5d4114b2485355d', 'nologinuser', '2019-09-18 09:54:24'),
('tou969bfad3123b5c77459961afa526d237', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:31:10'),
('tou96de418b12c7d565d3f873aa179b040e', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 12:10:32'),
('tou96eac1178ff4cabc551582b612fe1181', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr802e5a133a8caaf0c428fa88c6bc9eb6', '2019-09-11 10:18:31'),
('tou96fda8849e1262761d4cd81097d68b4d', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-29 19:00:20'),
('tou97014c152894f376759977fdfe0526ee', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:33:29'),
('tou9708103986d61660e8b7c76c64f4ea7c', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-17 05:57:21'),
('tou970d0341678c3e7d06fcb323ad7e3f74', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:20:07'),
('tou9723e7cba5f38be271f5bbf2c5f7f736', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 18:44:39'),
('tou97324cfc763b2606a2a75a4024dcce19', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:14:48'),
('tou974a6d5624bdee676e06dbe1154f1f68', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:12:28'),
('tou978e2ea8bbee8ddcf8cfae09ac27ffa5', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:09:18'),
('tou97a73f934134eab240a302cfe2b55f83', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:03:41'),
('tou97c7d35ced9cbc3264e2fc53a7aa69f9', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'nologinuser', '2019-07-01 08:00:20'),
('tou97d26c24b25633c5eea821e4c10b4317', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:44'),
('tou97d4aad5b8b6a5c1623acfcd82f60116', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-03 08:31:07'),
('tou980a78c2cdc3ababe464bebd682a7b4b', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:12:30'),
('tou98116da43d955605beb793399990cd22', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:31'),
('tou9817824446234b6a6ae151700a2b09b4', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 00:10:54'),
('tou981b48713b7d8ff8d259f8b42dd5f976', 'itm_40c6367fee11bc95a223027f129e2bee', 'usr47d6cafdbced2e9935d62d18d2952cc7', '2019-10-03 08:14:27'),
('tou98287ba7b75d83bf87f7b8f040c9a760', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:23'),
('tou98441fe1da6a111ca174276d36976fc9', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 16:48:43'),
('tou98581ae993e85a0e395f92303f18d85c', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:51'),
('tou9860fc9010d18c4eb434e8be731a327c', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:42:28'),
('tou989e073616d01298a7b5c3456e282e10', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-11 06:44:57'),
('tou98c2c3c0de364dccae26318108d43b1d', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:18:21'),
('tou98cc6d074da2021d0f2e195153a80256', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'nologinuser', '2019-09-15 14:07:13'),
('tou98f6a5c77b93db80deb65b37deb42939', 'itm_0500dcce80c7d70b79453a8ff4096428', 'usr9f42abfc8638a5e733125a12f37bbf2a', '2019-10-07 08:00:32'),
('tou99140b868eeb6055f1de901214ff9547', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:43'),
('tou996c901a75b4b28995685095f7848c43', 'itm_aec300faa4929e3342679e55e63c24bc', 'usre5cf4f22a2bb0ad81a5d8b492bdfdc05', '2019-10-07 06:56:27'),
('tou9992129afcf9497396e29e925a3ee776', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:26'),
('tou9992632b1d3b7c2db77568c402ef9c78', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 03:52:54'),
('tou99b13cb2c4c5f427a735d68bffbeb882', 'itm_d827ad45f61ccd9df35e24c1678ea6c6', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 06:38:25'),
('tou99d3506b4116aa5284155c1c4e803fbc', 'itm_0580d078a65d3887457b4941033ce226', 'nologinuser', '2019-09-04 22:48:48'),
('tou99d4376188772e5a66c56653a39ec1e6', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-06 11:56:54'),
('tou99e69b9c6615254ee88c0e2a5f0ad070', 'itm_aec300faa4929e3342679e55e63c24bc', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:00:55'),
('tou99ea91a5108dbd57677a797f6d95db54', 'itm_9ef57934867903960a9849bb7af133e0', 'nologinuser', '2019-09-18 21:12:20'),
('tou99f1286afc7b466598a682c3bd404cb8', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 09:47:52'),
('tou9a25a499c385fa7a2ca5ccfc03eb505c', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 10:27:48'),
('tou9a2d50680a3259215cc0d4fdb757555c', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-09-21 18:15:00'),
('tou9a380285e3c150a0e184cbf715cd1da9', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr84264292f0b3360143f6ea8776b77f20', '2019-07-24 07:33:33'),
('tou9a4688c4212bbbf41d452a83262696a6', 'itm_caf91ce095ca25085c5f89a45a7cdd65', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-07 16:10:42'),
('tou9a58f8ddb8d4e99aaf01ed9519ec66a2', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-25 05:20:56'),
('tou9a5b78d5f31fcae62db8405de2cb508a', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:17:37'),
('tou9a6b3b83676d9eaa1bdfc48f428a6d58', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:12:59'),
('tou9aea96d69028d3fba163c853a5486825', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrba287c1864e60a6def65e8fe3242d906', '2019-09-11 05:15:47'),
('tou9af04b7b02c6a48c8522861b1ac7c25a', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:34:55'),
('tou9b149aa642f08b82ff4a2c84a078edda', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:30:30'),
('tou9b2e00a7dfffe2e4279e030401cc3542', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 23:26:30'),
('tou9b4e509f253b85dcf2401c77ee2edb5e', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:28:33'),
('tou9b65f43777ad4c699a0ace7114c28833', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 10:58:07'),
('tou9b69f67acb10984d4df4f037943bdeab', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:34:25'),
('tou9bb29bbee14b1a2085c9d02d8715c657', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:32:08'),
('tou9be16d6612581c82b065493f7b65fad7', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-05 07:39:27'),
('tou9c613551b23b94815693551f1cc45bbc', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:23:11'),
('tou9c9eda1930272528cef0ce9318bab656', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 22:46:41'),
('tou9cc46b0518c4f8c2f3ad38984d428f0f', 'itm_d688553dcae9083a4104754662ec4d26', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 10:46:34'),
('tou9ce6f692d1130b2a72fe51444e982230', 'itm_8277fd5c824ab26ec25f902c7026a40c', 'usrbbb81d64f7d56bdc2d7c0dff91bd2346', '2019-10-07 07:03:35'),
('tou9ceb80ab30ca13ed14435c344d2c0b8c', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 13:11:46'),
('tou9cf25b56de13e9a5e5fd24aa4ac897cf', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:32:25'),
('tou9d5254c07f76966ef61cd323567bf5bc', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:08:50'),
('tou9d5c37f37a7c35031f8d4465c7a4baab', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 08:26:40'),
('tou9d89e319c07bb8212e361c50c293a7f0', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:34:33'),
('tou9d8d79c9e90ec03f35068e51cdcb812b', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:44:21'),
('tou9d9f2d1a49c9e9b5f28cd02ac7fc2e4f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-08 19:37:11'),
('tou9db17ecd4bce7c1ec5f98e2be84e44ab', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 04:28:08'),
('tou9dc66c506789f3768d6b4c0004ead3df', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:12:51'),
('tou9dccbe6f22a7dd0596f65f32b8418548', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 08:08:23'),
('tou9e2ae52b10f305a5539e62da54b827cc', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-09-13 05:10:24'),
('tou9e2b37af4283128980b42fc95554394e', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-12 16:11:01'),
('tou9e3a7f67136f9003f247cb8d74fe6298', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:42:51'),
('tou9e536473037bb465652d8322eb7630da', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrf31597aca589e0f1b43061cec1f399b2', '2019-10-02 07:24:35'),
('tou9e543db2a16e18ddcc0923fa61f75752', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'nologinuser', '2019-09-10 02:51:03'),
('tou9e578ab93943287377e1edbc4624a085', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:32:25'),
('tou9e5ffe54ad58ae3c0709490a4bf7bd90', 'itm_81e1b4c39551a5765743662e1f5f079b', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:43:49'),
('tou9e65387ec18ae1fbeea28d4ea18e08bd', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:40:41'),
('tou9eb8cb55e3122a24564b0a03aef3aac4', 'itm_02db30c4e6a968ac1955cba086d95b17', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:26:27'),
('tou9ef0129c5b877cde41ed48f0bf71e1b6', 'itm_bfd764c6d3b307679d64c24525ca85c8', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 20:29:00'),
('tou9f3a3bca877054bcab35a69addd0e632', 'itm_ba0837310e18f29408f09fca748271ef', 'usr84264292f0b3360143f6ea8776b77f20', '2019-07-24 07:33:53'),
('tou9f3c099a8701d8b44249f7e09b5476c9', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:40'),
('tou9f6c8f34e05a2be764c0db4c30ca4987', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:22:06'),
('tou9f6ed6272c684f78ed902007fae9f59a', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:20:17'),
('tou9f943ba732a502711b57481b5f841a82', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-29 10:24:10'),
('tou9f9c7bd1259f43ab4b784df1a8194da5', 'itm_7359de3d150f84bd9e06d9e6054ba151', 'usr1db69cc9b7c316666af1bb9d623e031a', '2019-10-07 07:48:52'),
('tou9fc75423d705ca62c271651e37c82912', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:25:36'),
('tou9febdbadedd36551740e41120e32c516', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:54:26'),
('tou9fed58285689144790b923311406fb78', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-05 03:40:21'),
('tou9ffc502e9573fd3379dd56a8bec742ae', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-21 16:24:34'),
('toua01d9e6871ec5bc97c5d5db00d76a06d', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-06-28 10:30:09'),
('toua030a133e525b9a99b8613f8f6956632', 'itm_8277fd5c824ab26ec25f902c7026a40c', 'usrbbb81d64f7d56bdc2d7c0dff91bd2346', '2019-10-07 07:08:56'),
('toua0327da844291de2ee7d542d60ab7ab6', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:39:42'),
('toua033fd1b62e120bd69c6c14a6bc0db42', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-29 11:52:53'),
('toua0795c5767e30b1e82c93bad4a14ed04', 'itm_2bb3fb13e1040cae112485657e31f868', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 06:20:49'),
('toua099ce206c150e9d080236fb9c0e6217', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 07:05:01'),
('toua0c0f475fcb1ca16465a0ccdc434cfdb', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-30 04:52:37'),
('toua0d8322b2e9e188f2b84968180b74dda', 'itm_10c058734f4608bb3b1b2c3b8a7853cb', 'nologinuser', '2019-10-14 08:18:47'),
('toua0ffc4ac28440d1f8d2c2bd324b41008', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 05:47:42'),
('toua12e6f3b7cbcacb4656d71f8b925807d', 'itm_81e1b4c39551a5765743662e1f5f079b', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:45:42'),
('toua1489281dc40ed8b1a621763abb67490', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr3f57bf66eac7eebcf7cbecf84651d96c', '2019-07-02 08:56:26'),
('toua19c93e8276a413757454e82f9558f71', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:25:08'),
('toua1b7087cbe05c91c9eb7d024309822f0', 'itm_02db30c4e6a968ac1955cba086d95b17', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-25 23:28:35'),
('toua1b839fa08da402e94e14f069385c733', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr802e5a133a8caaf0c428fa88c6bc9eb6', '2019-09-11 16:59:02'),
('toua1c4921bfb02d722eaa42581b91ec600', 'itm_40c6367fee11bc95a223027f129e2bee', 'nologinuser', '2019-09-25 10:43:56'),
('toua21b133a96a87e5c17d9019f0b88812a', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:28'),
('toua21bf888e0c29c330a6b78d49bddadbd', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-08-29 16:23:37'),
('toua237ac5b3f5771d857b0b0eae736fb95', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:15:38'),
('toua2393cd8a1303e19a6906b2e27038bcd', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 09:12:40'),
('toua25c3fd05a76d4ef2f7b86b2d34eec4b', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:49'),
('toua27e30fef245e8df4ed04f65f019ed76', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:29:41'),
('toua29bfc37227d5a0494627a89e53e54c5', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 10:30:02'),
('toua2a61cb63f149ee6299bc41e66a34331', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-10-04 13:24:20'),
('toua2d50090ea741f591265148e77d8ca8d', 'itm_92994f957013924861ccf81e6a8ea4dc', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:49:10'),
('toua2d8157d5dbff8d7c258d5037cd3a766', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:34:55'),
('toua2f73738c77ee372168a3cb4db53bf2f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:06:43'),
('toua3017550a01e60c5e99b2d9304c0c596', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'nologinuser', '2019-10-14 15:06:43'),
('toua305b7cfc0572cf21c0dc82cb3cab8ed', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:25:33'),
('toua308b0669b346b6e5a3a85d824a438ce', 'itm_9ef57934867903960a9849bb7af133e0', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-16 04:44:49'),
('toua348c9c1a4878b13677c8de3c1a27d08', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:23:11'),
('toua3b17173403c849408eb483e1da678b6', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:10:22'),
('toua3bc7e39a8e23d18744078b798713ae0', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 10:58:10'),
('toua3dd377849e08150b0dbbe20f4ff558d', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 04:28:37'),
('toua440ec2a9119ac6ed2a90371992da0a9', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:36'),
('toua452e6353773b13ecf9ee571a2f7441d', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:29:35'),
('toua4659b427c221eaa681896ecaa9f56c4', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:25:07'),
('toua46f38de5ce6aee47b95579d204eb3d2', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:49:01'),
('toua4abffa738a8afc8e686687878b0718c', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:50:59'),
('toua4b6b9639ab710bbb773464de3982d61', 'itm_53e724271a6494108e270b923a2e8408', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-30 13:37:20'),
('toua4bb9f1007d7539e4db0e4abc400aff4', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:16:46'),
('toua4fac2a6fa32d027a72f5c892ac42b91', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 21:32:51'),
('toua50421a64ff7890ab782ba2b68779141', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:30:40'),
('toua5170fc16097f8481607a5aa29c359af', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:44:36'),
('toua52147e29b80dddcecf0dadceecc69da', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 04:01:30'),
('toua5237dc09d08d2ca73230260e5e2dcfa', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:20:31'),
('toua5f14094c2fb920f182c03f1183cf426', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:55:14'),
('toua5f53e0e8dead3be4c409606d93a3fc6', 'itm_33a66429f209514b7fd2c128fbf1f88d', 'nologinuser', '2019-08-30 05:40:13'),
('toua6091fc179f85c2c08b7ec96e2a00313', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:14:55'),
('toua6094c8c71c1300efec2d40c452e5513', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 12:07:26'),
('toua637305f2491c3f9845564d2f143f354', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:07:42'),
('toua643dbc7bd34db5eebeb06f828ba2aea', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 22:16:26'),
('toua6448f40e1615f6659757516e0ab272e', 'itm_3574960a0f206a81307cfea2804acbed', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-25 23:35:51'),
('toua648380c86fd36d51608d93525b091d3', 'itm_b18cca37df85a01418739145d4369153', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:08:29'),
('toua64b7a89e8209285a62289e2686ca970', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 12:02:48'),
('toua66c9873d10fa4498ba3e29ef94874b8', 'itm_715fee0a325de85667e2271e03d8c30d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-29 19:00:16'),
('toua66d318ca5aa94e542badb275bc7bd1f', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:29:43'),
('toua6903aba85da9105adf0c58d0696967c', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:28:51'),
('toua6a97afa743d41b9f49ff7be120f9e52', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:32:21'),
('toua6bc95b0793a83a55ee9e114b46ff197', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:31:37'),
('toua6ce7e16245718c0217ee3449b781710', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:54');
INSERT INTO `bs_touches` (`touch_id`, `item_id`, `user_id`, `added_date`) VALUES
('toua714c786762f82adfdf58e2239f6c8cf', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:10:53'),
('toua716efff4e0b865be7d10bec455114b2', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:13:23'),
('toua77f872d424ca2117f6281cbdc4f926a', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:41:05'),
('toua78c177e2969ba1ad67a37c90009919b', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:37:40'),
('toua790a74026d9662e59e196a825dc717d', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 07:01:50'),
('toua7a93973bd20fc46b6b48b1ab9893828', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:06:58'),
('toua7b4bda02d66c3b4f214b42f2e5ac654', 'itm_19d8a632ca279d2a4739913172cb71dd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-24 04:47:57'),
('toua81c9fdf0fb2ec5e36ecd7a646483552', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:25:32'),
('toua81feb0bac4956914c5789e94cba969e', 'itm_45f53a2e646e484d6037e562f4233cfa', 'nologinuser', '2019-09-12 05:58:20'),
('toua829c140ad9b4ed479507b5bb5b13492', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 11:00:04'),
('toua86e0892ac98f40735198cb1f900414a', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:39'),
('toua8761425b89e399f88731b9b2aaaffb1', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:41'),
('toua896e5691195410b46dcb01ce72c1a11', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:14:53'),
('toua8a8bb935087938d376a89b7f701d369', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:30:39'),
('toua8cb72f7d61bb1dc13045796a31574da', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 10:10:31'),
('toua943c5181c1a3907799db1d8aca28a73', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'nologinuser', '2019-07-01 07:00:44'),
('toua94e4b9c6e25c6ad4677cd9c58e84b83', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 07:54:00'),
('toua99c29452d52c21cca8f55afba36f6c1', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:12:11'),
('toua9c9f98d400de9baaed074beb59c68dd', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 05:34:35'),
('toua9cb1653412fce00d28d3717e89a74ca', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-02 11:56:55'),
('toua9cd24319710a444087bd451e7b8fd4a', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:30:29'),
('toua9d73ba19d7892bdae14332590eacd81', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-27 05:17:50'),
('toua9f879bc02ea663fcf576764be77656d', 'itm_ba0837310e18f29408f09fca748271ef', 'nologinuser', '2019-09-08 07:51:43'),
('touaa11b7d7327b799dff3c198922e94c84', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 07:16:05'),
('touaa677dce1a55981c06dbe7b3ce3049b7', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 06:08:06'),
('touab090d215813db38002773da17353996', 'itm_f84567fffc92857a220e2f1eb89f9971', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:25:56'),
('touab2d71c8590a59441315e0f4c8dc61ba', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:13:38'),
('touab89a58fceb4093ed7cb55a9d408a03b', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr3f57bf66eac7eebcf7cbecf84651d96c', '2019-07-02 08:58:45'),
('touabc1bf0ba54cd1a0e429b8a43de589c6', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:20:02'),
('touac07d8de0ec6e17fbb7968230ec53c0c', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:29:59'),
('touac083b66695b08da86ed3d5f2576a9ff', 'itm_f84567fffc92857a220e2f1eb89f9971', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 11:02:57'),
('touac0e45b4be6e406bb1821e26f0000de2', 'itm_ee73165b7c94f2eff3a4201b9fe73c6b', 'nologinuser', '2019-08-29 14:37:29'),
('touac199df468fc36525ff2a1d24a8fe259', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 04:41:46'),
('touac2f89beaf5b920d092cdef1e8236e14', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-29 10:43:44'),
('touac3e9549c2c2d5b5b2aa734ed8eb788c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:41:06'),
('touac4d3024a90cc53fe5b7806758e567d4', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 10:54:08'),
('touac73a5da3df9f911d1fe9918378fa1da', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-08-30 05:27:11'),
('touac75e9e414f83bf4d0dd35b27372e7e3', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:05:14'),
('touac76d655f3e6155cb52103765755b93a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:28:03'),
('touac92e7475edae18ea942eac89437df0c', 'itm_f84567fffc92857a220e2f1eb89f9971', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:25:49'),
('touaca1408abd99d124991f839da7eab284', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-26 16:38:31'),
('touaca2d935dae61fb8887fed6cca90401f', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:28'),
('touacb9388067f6e1d7ee1459f6570ecc57', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 10:25:21'),
('touacf00725efb3d38b13609c26ffa5409a', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:52'),
('touacf955c52eb4d6152e9505b8743fa6e8', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:22:24'),
('touad01b5862414fdecabe264455a827451', 'itm_3574960a0f206a81307cfea2804acbed', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-01 06:55:59'),
('touad199a1bb2217cf6bcd458860197aa70', 'itm_feba75af4918ebee809bafb3c5e4c963', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 06:54:07'),
('touad257a782e8ef23c85e2063bb5a30d45', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-10-03 03:26:32'),
('touad560599e1d2e86a0b5a75ce6efbb564', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-19 10:41:53'),
('touad68ee98683166af075f28bce5b87834', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:46'),
('touad860e98860abbfe90172ed4994d7894', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:27'),
('touad8bc07bc0d6737e465b3d06fb873602', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:53:54'),
('touadd26e686a4e6c004799bf59c7e87bcd', 'itm_10c058734f4608bb3b1b2c3b8a7853cb', 'usr7e1473b2561c8c5a5b2fc2a0b4c3b732', '2019-10-18 05:07:41'),
('touadda770629a3314b0dfbb967f7d989be', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-09-28 00:21:19'),
('touadf0bc0d25055a6984dfb1d97cf14816', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:30:51'),
('touadf2448fbf9a10351d69020a2d6085ea', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 07:17:29'),
('touae05e643c0af2f27ad31975e8ac3efce', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 09:40:37'),
('touae240e16907b9b89d8a69246ce781a3f', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-16 15:26:01'),
('touae35ae0b48ece5210d0d325b5ac3f2fb', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-04 03:23:58'),
('touae446e02743946cab3d8db99f1409baa', 'itm_52c74ad65651f004b6145559b25db4d5', 'usr045282f5e5ebc1be5ab97223d4d19363', '2019-10-04 10:43:04'),
('touae4e6b9e933fd43f8c57bf05b9b0e869', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-07 07:08:31'),
('touae69fe3f05fc757ab0ffbd5e0f9708b3', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:33:54'),
('touaed97e338df7a5e0c808c4be9920661d', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-13 11:23:13'),
('touaef8b540b26d482cd1ac3a4473e61aa9', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'nologinuser', '2019-09-19 03:52:20'),
('touaf1f2c236476353e1492d7019fc47c08', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:22:20'),
('touaf508a75546924f849286e364469a435', 'itm_ee73165b7c94f2eff3a4201b9fe73c6b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 06:38:39'),
('touaf61dc375861391ea8b96201db96d1ad', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 07:44:12'),
('touaf7ced879e661b875360b6fb6d956c84', 'itm_b18cca37df85a01418739145d4369153', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-04 03:23:47'),
('touafaa108c4efc7f74431afe8cb76fc8a5', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr8f466efaba2c555c63f6b739db4f6f6d', '2019-10-04 09:07:47'),
('touafc4e42571fe3ba80d172472da91652b', 'itm_3a197e7a07668e4d8580e5b129957107', 'nologinuser', '2019-09-17 04:33:59'),
('touafe24ea5dac5b7e6dac45def8c11120c', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'usr8ad862ee8b0eb6495d389cfe6f35655b', '2019-09-16 14:34:25'),
('touafeb7e26c8d06b0f67a6e789ce953695', 'itm_a0ee873a872f79828676b8850c3e875a', 'usr802e5a133a8caaf0c428fa88c6bc9eb6', '2019-09-11 19:02:24'),
('touaff13c96ce5b3a0f07305d5bcd44993b', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'nologinuser', '2019-06-22 09:11:12'),
('touaff6b7e873f54f4887383d5fe6afd5c3', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:59:53'),
('touaff91450ad792a05593575a874a38c39', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-03 03:26:45'),
('toub02e5a93764812a81be00aae786098de', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:22'),
('toub041f9af91c233a6b912c75d32eff9dc', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:05:16'),
('toub05472a5b3af7e58c87a08abf4d17744', 'itm_3a197e7a07668e4d8580e5b129957107', 'nologinuser', '2019-09-17 04:50:47'),
('toub0573a3c835ace01c7ceb52491362d26', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 08:05:31'),
('toub05aea4b84d20f56088237a51e80928e', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:11:37'),
('toub05b00d836c4a2c366cf72f2aaafd9e6', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:00:56'),
('toub05ea94344e30c15fcb770fa7a0329df', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:32:46'),
('toub0651fb265b934abf25837ca0d7f3905', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 04:41:20'),
('toub06807b502666c880256e08d666a5ef7', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 07:47:08'),
('toub071995ed5758d33e2e43d03f0db5795', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-02 11:56:50'),
('toub07266be0c3e5b1a1c0e873f7fb615a9', 'itm_79324075063082372ebcb5b759b2bf7a', 'nologinuser', '2019-10-14 13:53:22'),
('toub0deaa552c3a19e41a0d3dfdade005b7', 'itm_709970eef73e104ca5d4114b2485355d', 'nologinuser', '2019-09-25 10:54:26'),
('toub10177572e6bd803f88329eee4e7fff1', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-28 12:10:00'),
('toub134ceb7b6972dc7e5e5db40b2b62cf7', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 15:27:07'),
('toub156d6ab81cce38bc31bbbdc45a030b0', 'itm_33421d314109f37e8cff807cd4149cee', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:55:51'),
('toub1609e0982cde610b7bd2773ceb43d8c', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 09:53:04'),
('toub1b4e007fbba72254f8ee50a8d1eb053', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:24'),
('toub1c3134a235f3861768f692a427cc1f0', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-29 19:00:31'),
('toub1c6e5fe98b70ccb9083f85ef73d06d1', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 22:39:10'),
('toub1e0bc26d2e43c2d751793fdac3c20f5', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-13 17:17:45'),
('toub1ed172288f073dd6d9ab078846798f9', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:51:06'),
('toub20b6665ac3f287e1ac80d2729de6017', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:00'),
('toub21946b0913d6afae9470def0d435225', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:10:34'),
('toub21af64073765c29464c1ea33006a9a7', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:01:49'),
('toub224b6d3c9cf910183d53ac0d7695d97', 'itm_54709837e70ae2f179b49a739ded54d7', 'nologinuser', '2019-08-29 16:43:34'),
('toub22a1a72aca5e79eb7ce6e84fc41efbe', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:03:24'),
('toub2324823b4fe3f543c48a278fb36b8aa', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'nologinuser', '2019-09-05 15:15:37'),
('toub251b2f34a233c622acf12e561d0cd98', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 10:56:05'),
('toub25501e9583fb2aa83f692bded44ef13', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-01 07:04:54'),
('toub255410d38be0a8d2cc27b07d5fe146b', 'itm_f84567fffc92857a220e2f1eb89f9971', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:32:35'),
('toub255f02dda68d6880914c12991106a0d', 'itm_5058fb44b1f3a2efd9cbd0925ab67785', 'usrc58fe4f5fea11c19ee38cc1de75949ea', '2019-10-07 07:27:18'),
('toub25f274f1b010f8a207277aa5b676c83', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:30:33'),
('toub29a8054cc20dda3715daa3fff95936e', 'itm_0500dcce80c7d70b79453a8ff4096428', 'usr6ee26c591641265015309ee4300aee9b', '2019-10-07 08:02:51'),
('toub2a07fa09d7077f99994d71ec2a323b1', 'itm_df00498f0ac018c80372cef801513700', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:29:17'),
('toub2d3319b882410a4d0b205301745aa61', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:39:35'),
('toub31264d92ca88d8a4a1f1be6e3226ce5', 'itm_53e724271a6494108e270b923a2e8408', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:22:59'),
('toub325eba2270c754eed168fed4cf62e2a', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:16'),
('toub3374c818b18052a86d915b83ef2fe5e', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:08:44'),
('toub34577ec959e4ad97b278c8786d44fda', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:38'),
('toub3874eec12c433f7639debcfad7a82a6', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:35:57'),
('toub3b02eb0adb528f68fcf1d29acb80b58', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 10:31:11'),
('toub3cbba974175868af654553e8eecc2de', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:08:47'),
('toub3fa96626a43a669c8edbae18be29948', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:29:47'),
('toub40306cce4f0930d26968bb826922848', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 10:44:29'),
('toub434ce639028680d0ab68a5604a6df6b', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-05 17:26:26'),
('toub43b168fcdcff73c1a82f73e3a49e7d4', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:24:57'),
('toub457b655c88e2f13e79c8fc822a55ab7', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:03'),
('toub4909d7ebc5843fc6251f5236750991c', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:00:57'),
('toub492baec79e4354d4cc188a5759ef88c', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'usr9c7a5d7887d53caba5ba4d94bf1b45d9', '2019-09-08 01:59:56'),
('toub4a98e81ee172b4fa7ff19d97d757bae', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'nologinuser', '2019-10-06 05:01:50'),
('toub4c59adfae52c65948556f714b749e25', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 09:59:25'),
('toub4e81033f92420fd005c16641b4efb62', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 12:10:21'),
('toub4fc7ab6ea6fb31cae73d5422947bb90', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'nologinuser', '2019-09-11 19:01:27'),
('toub523ce58ec08100fc40e6692782de280', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-14 00:18:12'),
('toub545789a5ce5da4cfe4053817fd3bbe2', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 07:12:32'),
('toub5459a09578856b9c0870ee340196352', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:41:56'),
('toub56725b6a84dbb172afc56454a5cc7c2', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-28 12:21:38'),
('toub57f49ef1c0f1fb1042a2f516019c5eb', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:06:56'),
('toub585dc4095e99adabec7024de08ac9f4', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:03:30'),
('toub5869a8601f08b5ee4ace44ca760ac59', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:37:47'),
('toub590cf7ae33d89236d3de0c13ecb72e4', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:32:33'),
('toub599e6f1e419249576266f13d05c177d', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:22:54'),
('toub5a7f9237f6be9346ec7029b5a810778', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 05:31:59'),
('toub5ad077d5c463852c5a9a9575f40f48d', 'itm_228aca47caf96513d300aba7aa9f2fbb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 11:33:37'),
('toub5affb24f3bb242c822f79ea6383afcf', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 03:53:13'),
('toub5d8861534267cf0d21bfb9e53f44e91', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:02:23'),
('toub5db193c4f2dc102774d649fe7a5014f', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 07:28:42'),
('toub5e80be4b3ada27a4874f0e9cab7a103', 'itm_941519b2438b0747386a6fee39e5b279', 'nologinuser', '2019-06-28 10:32:53'),
('toub5ec80d1b6c74a9fc77d87e32ac90dbe', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'nologinuser', '2019-10-04 10:16:58'),
('toub619f09e45249c62e4ba3aa8556a475a', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-11 08:30:16'),
('toub6267f5e103ae9eb047bcf1a4ebcc46d', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr1c30bc352b4dd71bb0469d1b02a65da3', '2019-09-19 10:56:07'),
('toub62874ac3311ef9b11aca4f7ce5be698', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 08:08:14'),
('toub62f20bd784a0683b1d772ef4db02a9a', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-02 22:15:10'),
('toub63d5024da06331398e1f3b521f09cac', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 00:26:39'),
('toub6810aa19e8019c02d907093815e2587', 'itm_45f53a2e646e484d6037e562f4233cfa', 'nologinuser', '2019-09-10 21:10:00'),
('toub68606930242fed66c94dfa7e6183339', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrc4ee43195313d1c2d59e57828d61dec9', '2019-10-02 07:12:45'),
('toub69163d8b6c37cbd5a3ffeba64c26e81', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-17 09:34:43'),
('toub6997547ee485b2165f14bcc187e7c30', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:27:05'),
('toub6cc7de7860eed86138e0a78677c4905', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:01:02'),
('toub6eacd14881c2b95d859f5cb638c51f6', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-04 05:00:36'),
('toub6ef6b15a4e3bad5c159acabaa74277f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:10:09'),
('toub71137c8820d23ff6959a2fabcef5fec', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-09-12 18:25:40'),
('toub718833024745b0e8440d729cfb558e4', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'nologinuser', '2019-09-11 16:58:06'),
('toub72e5b8036ec777a0150d59902d3fd76', 'itm_40c6367fee11bc95a223027f129e2bee', 'usrbad733f0fa26f94bc0c072a39d369a8f', '2019-09-22 00:45:10'),
('toub746e2e21bbbb52b0bd7f299ace104b2', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 10:11:10'),
('toub771f2ddf3e10662ac81d1b77abe7d3a', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:05:59'),
('toub7954f4ff3ad00060055cd6e9b18d3f8', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'nologinuser', '2019-10-19 22:49:27'),
('toub7ec0648faaafc052b89e8bcb32d519d', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 04:28:52'),
('toub8231d7039a2e9b5cfb1ca5c42044225', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:25'),
('toub841d742ba6c68006e9ebeef64792c87', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 08:06:30'),
('toub846f8caa230c37ca6261a6063034c41', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:20:36'),
('toub85681b9c6e78eadfb3a768fd864944d', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-28 12:09:48'),
('toub86d2a17eb5bcc92289510d02be6e7ef', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:12:19'),
('toub878e1cfdfd18cc2fef46a4d37f1494c', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 22:33:26'),
('toub885045531df6efd038e574ddc9503e3', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:22:24'),
('toub894825d2d32e5f1eaafb9fdfd5b5648', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 18:47:52'),
('toub8aa2b02cb360237f1f75bb8d8513d86', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:56:14'),
('toub912c38152b669118e1592fa61b1c78c', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:43:36'),
('toub916973219d7d30f857dd686f899e37f', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:35:28'),
('toub91e86da55b30de95a3aee53c18e9ae9', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 09:59:28'),
('toub92febe8b7db077f35ae1ba0e9680b8a', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-02 11:28:31'),
('toub938ac54e0a44e493739c06eecc6df5f', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-26 11:23:04'),
('toub96d30cc859528cbc77f4f95a629acce', 'itm_aec300faa4929e3342679e55e63c24bc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:25:32'),
('toub971386c175954fdbca93ef8e82698b1', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:50'),
('toub975fba4607f67308158139adb7ade84', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:08:11'),
('toub990d638be967d150e7084f339994716', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:31:45'),
('toub9c11d208c32a79d733c44a80de4f707', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:23:06'),
('toub9d50bfbbfee2d02c0565d12520f4cca', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:01:27'),
('toub9dfd9d41809ebdffae41291f33e5f61', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:08:50'),
('toub9f63c5941903404b49792227830bfee', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr802e5a133a8caaf0c428fa88c6bc9eb6', '2019-09-11 10:18:48'),
('touba30b89a11afd46f239409d1e6f1fa5d', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:19:03'),
('touba4d6eb8e1c7f750d113952d852415d2', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr3f57bf66eac7eebcf7cbecf84651d96c', '2019-07-02 08:57:38'),
('touba78c726114433eecfb03141e5dfbaa2', 'itm_d226e2115d5632e9aaaac89bd880737d', 'nologinuser', '2019-09-16 15:26:54'),
('touba96a9a221a32acccc9a3e7c3bc532fa', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-18 10:47:06'),
('toubab57892da44bef894abf48ea42cc46e', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'nologinuser', '2019-09-12 16:09:19'),
('toubab84e25bd0ede71b0e7481880ec9ed7', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-04 05:08:17'),
('toubad7405d5301d2b3c5b66c3fdcc863ee', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:27:44'),
('toubb2cbc8a04bc82b0980e3d2e7bc04353', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:26:19'),
('toubb59676f4a42cf3cad452064619a25df', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'nologinuser', '2019-09-23 22:18:21'),
('toubb5d32bdf10b886b7bc3142445913924', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:49:46'),
('toubb6f2bb77d78613c9d9c2a22126b64e1', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-12 16:09:06'),
('toubb7d632d959a545da6360a6a34f5b646', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:40:45'),
('toubb7e8c4285821b2509d42176f82498d3', 'itm_54bbc74813945af6146b97de97927c7d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-25 11:31:45'),
('toubb818b869c167909f8aa877f5038b180', 'itm_feba75af4918ebee809bafb3c5e4c963', 'usr2143bfa9ba787c44d54468c26d7f485f', '2019-10-07 06:33:08'),
('toubb860fc847d3db28c19fef3a54ae4112', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 10:27:28'),
('toubbac7099f362f8b47ba754f2f2d4a541', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:20:48'),
('toubbb0084ae14e5502f7c4191de86a8852', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:36'),
('toubbc26ae71327d4236dd32b8d587e1fa5', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-10-06 07:40:29'),
('toubbd8637fb877beb65ec0ea1d9f45c7f0', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:22:15'),
('toubc2fb18cd5e83785963ed047572fbc2c', 'itm_e484812d33f6ca0a25e3143683d67688', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:28:18'),
('toubc306f09905b970ca7b80a8c630aead6', 'itm_9ef57934867903960a9849bb7af133e0', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 01:15:26'),
('toubc3b4b8c62482e226ba4f37c0763fe8d', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-01 06:54:42'),
('toubcc939ea0e8ffb35ad608d3414ea980a', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 22:53:49'),
('toubcd2ee5cf08aa6405a481d6ee32ce05c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-15 16:31:30'),
('toubcf78d9a5df52fee70b26b5945c40047', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:13:26'),
('toubd0972a2ff604508f886d47e55e4edf2', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:38:36'),
('toubd16af0cb5266a136020bcb424c11099', 'itm_0580d078a65d3887457b4941033ce226', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 12:03:24'),
('toubd1c372a447d4b353f75460126e85d60', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 11:01:16'),
('toubd34a01a6e6e28ab5c0b67645fb680d6', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 23:04:38'),
('toubd487af15c7a88a00d185ab9252a004d', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:47:16'),
('toubd4ec7d4dc1c7a5983ab2705b395b951', 'itm_81e1b4c39551a5765743662e1f5f079b', 'nologinuser', '2019-09-04 22:55:22'),
('toubd55edcb3c82fb64c8c3231d96a4b9ec', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:39:24'),
('toubd61d7543b33190a4449cd91415e819e', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'nologinuser', '2019-09-12 16:10:29'),
('toubd85111b59428ff5e9e2bebdbfe16ba4', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:11'),
('toubd9658da50c58043654d31fbdc262e24', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:11:14'),
('toubd9a7194e099d9a2152ae082e4883b3d', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:10:15'),
('toubd9cb2f636786fb8a1c0d567c4edd027', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-15 12:18:33'),
('toubdda26d5fd278c6bfb20c99b3a7b1450', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:10:41'),
('toube13e8b357990f393c641264ac9b36f8', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:40:29'),
('toube40eeb01fd2d8df8459e3faaed9ad69', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:23:56'),
('toube576bbdc6ef668d050cf3482a98338f', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-03 07:48:59'),
('toube5aa0b08526473682b9491c87215cce', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:25:31'),
('toube643052dfa24d5f7a8be103218fa8d9', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:23:50'),
('toube676f7b3ba9cb2951a1fbb59bba848d', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:27:00'),
('toube8efb3b9570078d91303d6641177a73', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'nologinuser', '2019-06-29 05:30:49'),
('toube9d21fa8041505456d7071ada36655b', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:35:49'),
('toubeb954bcc5b89c341711696ede1ea16e', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:01:58'),
('toubec30755da5b0f23adf96aba4bd15d68', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 10:32:07'),
('toubec7ec6e1cc7788d4d92b06cf3f424a9', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 00:40:00'),
('toubf0904fbae41466ddc31418aa2352a79', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-25 04:48:30'),
('toubf5e6b1ad62430afba29892d24d22e87', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'nologinuser', '2019-10-01 10:36:22'),
('toubf8f0eb830f4c2bf1958603a4b027316', 'itm_33421d314109f37e8cff807cd4149cee', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:48:45'),
('toubf9dcdfd1fcfb7f14d2d8dcd0c4c778e', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:41:30'),
('toubfa68bfb4df7c2f4bb653e97bbd3be3b', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-09-17 14:23:52'),
('toubfaa9676d5202a942e2cf22a854cfbb5', 'itm_92994f957013924861ccf81e6a8ea4dc', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:48:41'),
('toubfe3b307656273f362afac6e05769bf7', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:20:07'),
('toubfe4a6b6b595897fb4595852029446ed', 'itm_80277b3fe7993feaffaa1c77d5ef5d33', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 21:23:47'),
('toubffe783feb5a7807b3832fb3a247a13e', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-01 06:58:31'),
('touc007d64b52a0575b22a5dba52738c7ae', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:26'),
('touc012f8696dc25ff118f8e29eb2d20434', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-07 09:57:20'),
('touc0198a485f5d478771ddb0106560b87a', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:49:55'),
('touc02c0e473225ff0bfc404313d619d0f6', 'itm_e9584f6420da26e2378adffc455f7431', 'nologinuser', '2019-10-14 20:35:38'),
('touc05e358cbcda548bc91180719ccad4c7', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:11'),
('touc06e9c63367feb6aa387d2328de41359', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-30 03:55:39'),
('touc07d7e2192198306f0dbe0819de630da', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:01:45'),
('touc090fcb08e3b6c3ba4beac81e0d68f38', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 14:03:07'),
('touc0c8d5246eea676ed5d3e991ac5e590d', 'itm_54709837e70ae2f179b49a739ded54d7', 'usr0af0a9e866854e1205b104df27fcb0da', '2019-08-30 09:00:20'),
('touc0d3aa8c8092344c13ad2800ab177845', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 08:28:41'),
('touc0e8ae424263bbcee7deb7399d4118e7', 'itm_f84567fffc92857a220e2f1eb89f9971', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 08:12:20'),
('touc1268c25a8594197a6b70a98e61fc73e', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:09:23'),
('touc12eac1d5904ed15f2eff5645a508ed3', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:22:36'),
('touc14fc02a71db4a3a7f77e85b4ab306c3', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:19:58'),
('touc153fbaa3d0c5f013cce56adc2e5a837', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:13:22'),
('touc15904ecb67756bac20a83180fa3c17f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-09-02 06:31:08'),
('touc16283f36144b88a2f7ff30f167dca50', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 08:24:41'),
('touc19bbb98c9810b3dde81d0ad8ee85ad3', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:27:20'),
('touc1a4a084fb9f0c00ac9130dd983b644c', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 21:15:28'),
('touc1c5cef777bbd7415d538fbff28f2ec0', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:48:09'),
('touc1c9ac87ade537467e066b139c80a26a', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr802e5a133a8caaf0c428fa88c6bc9eb6', '2019-09-11 19:01:41'),
('touc1d47dbffe4aa999cb2bf7cc59438827', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr706a71c3e5bb7b0f5297fc0ec706a61f', '2019-10-04 08:54:00'),
('touc1e9763e11c076861483542117a29958', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:04'),
('touc21007c458b78bf755d7b978dc6ffe3c', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:23:08'),
('touc23285323112531b2ef33d447051653d', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 05:18:48'),
('touc24e4bf01736e9b9a2373ef34e3898bd', 'itm_d226e2115d5632e9aaaac89bd880737d', 'nologinuser', '2019-09-04 13:15:11'),
('touc26b71e08f82d2003c58ac4632301a02', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 10:53:50'),
('touc26bfc17ee494e37ae6e0335fc442f04', 'itm_b5372f02c59649e04d0639862f151119', 'nologinuser', '2019-06-24 04:34:18'),
('touc2b36cd3e6e70f7cc1e36365c4d3a5e7', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 12:14:05'),
('touc2fea01b44bd18ea5a929c9dd0790ce8', 'itm_53e724271a6494108e270b923a2e8408', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 22:16:05'),
('touc32900af0e815fe06b1c8fbc58ab0c0d', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:48:33'),
('touc36f759feae5d2634450e4c6c142e785', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 10:39:30'),
('touc399a0af74bbdad440d71d7721b01225', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'nologinuser', '2019-09-10 22:35:14'),
('touc39e5f0662f15f446604ab4d33b68881', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:14'),
('touc3aa6d68f3d7e31cc61f22174df0b7c9', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 12:07:08'),
('touc3aabbaa2b5b1e1d3729e69e598f4aad', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 03:52:19'),
('touc3e5b9993a7ecbd5bb789e02c29272fe', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:29:39'),
('touc3e7b5b32c17ee06291d2b523e8bff89', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:26:35'),
('touc44d91ed52fe4efff0420833af53d6e4', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:08:00'),
('touc450dfef34ccb4b23785cd57fde6fd23', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:15:10'),
('touc4526a88e5619a4b309bc9c72286b662', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-10-04 13:24:55'),
('touc463338506923a9c92af33a9390102ae', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:51:49'),
('touc46bbbaa71fdb30e5fb4c4a35ad79a7d', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 10:27:01'),
('touc473bd006309024dd49bc92a6a2c0887', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-08 07:19:29'),
('touc49e0ff73f682a6e863038a6ff6344db', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:39:30'),
('touc4a749acfaaefe9a33f94526b92e1ce5', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 22:52:47'),
('touc4af08a45ff52e9da96a782bc84a7fe0', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:37'),
('touc4b639976ad4512e3af10b882010d2ef', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:23:52'),
('touc4c6b5ea8c5f184ab46db9fa24b0ccd9', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr8f466efaba2c555c63f6b739db4f6f6d', '2019-10-07 03:57:53'),
('touc4d12bf163b9838870eee8b4bb8aff54', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-07 14:59:10'),
('touc4d69763304c9ed523554865cc85bd4d', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:08:05'),
('touc4e849fa25eb57ea5397287a15d405a5', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 12:00:44'),
('touc4f34964171e3fd2c5b899991c9ed3f8', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:13'),
('touc4fb516e782b601598703937232641d5', 'itm_f84567fffc92857a220e2f1eb89f9971', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:03:36'),
('touc541f512ed474c5320333137b17d45a1', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 16:48:11'),
('touc561699bcca446a438024a4ebde43dfa', 'itm_d226e2115d5632e9aaaac89bd880737d', 'nologinuser', '2019-09-17 04:51:35'),
('touc58a9de44043f708e2251afdc3337c01', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-08 07:48:33'),
('touc5a0c25b530cef775118dbeb3bc425a5', 'itm_715fee0a325de85667e2271e03d8c30d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 13:23:44'),
('touc5d06e99d608758e076d502c52c0ac56', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:22:32'),
('touc60a9d1432996e45949ff25ddd704a07', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 08:18:50'),
('touc617cfc0c2e21fd29b79efeb8db54aa1', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr1c30bc352b4dd71bb0469d1b02a65da3', '2019-09-19 10:48:44'),
('touc61ad177162c14302b6b2b0d3700d4bc', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:20:28'),
('touc62c4bc81c6975f3a943730aa52eb211', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 15:27:35'),
('touc65794a098f5fa58249774780c33e928', 'itm_df00498f0ac018c80372cef801513700', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:30:28'),
('touc65887fa4ddf6af3a1e08f28fcebac75', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'nologinuser', '2019-07-03 03:33:47'),
('touc66444bc011e58840fc49e2ec0beef32', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:30'),
('touc680b65fc0aaa5ad4a6e2d234c50af05', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:12:34'),
('touc68ca896a50f14264d05aadb71027c61', 'itm_e82bfe1a2f355582dad722ebc2f3851f', 'nologinuser', '2019-10-16 05:01:56'),
('touc6b8b21c801ace6ee5d9addb611e050f', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 21:23:01'),
('touc7277c60aefa4fd9431428c973303ee4', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:46:51'),
('touc74400f7e39184c1f043279daa9d31f1', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'nologinuser', '2019-09-04 13:27:57'),
('touc759f3ce3c3eb4806a001e31fe45d7bd', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:32:18'),
('touc777f52eb106874acd239dbae83b4885', 'itm_7da4b726eeb3a492413789219c83c6b2', 'nologinuser', '2019-07-02 06:01:38'),
('touc786d71bdbbc2fa25f5fcb354876e425', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:28:06'),
('touc78ff0afe7deee0baceb3402c1547b1b', 'itm_02db30c4e6a968ac1955cba086d95b17', 'nologinuser', '2019-10-04 10:11:46'),
('touc7a438fa2a763ad1c18efd87e81f7ad6', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:49:51'),
('touc7ac105626c7e8f6501272916270d881', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 06:01:53'),
('touc7beadb20fbb8849f3d309fdf15ec47d', 'itm_52c74ad65651f004b6145559b25db4d5', 'nologinuser', '2019-10-06 06:54:36'),
('touc7c0b82e8c8e1dd04af3b009ccb8a9d0', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:19:41'),
('touc8047c1f72e4d048380e0d48bc89268b', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 10:43:11'),
('touc8070eced37d857d2ec968a951f903a2', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 08:07:00'),
('touc81f5074e279ec18190650ca93ba76c1', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:22:28'),
('touc834080a1ceeed450e37dc7b45234338', 'itm_f84567fffc92857a220e2f1eb89f9971', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:00:33'),
('touc852f96033c094892fab73ec4f18d58f', 'itm_52c74ad65651f004b6145559b25db4d5', 'nologinuser', '2019-10-05 10:27:31'),
('touc898362279992b6c61c415053c7922dc', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-10 11:55:51'),
('touc8a019d52ea2fded0ac8ec601d629c0e', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'nologinuser', '2019-09-11 09:39:45'),
('touc8b2308541222484e0caab887dab9d7e', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 16:53:20'),
('touc8b8a33a8cbad2ce9fe3e78807d500b6', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-04 03:23:21'),
('touc8d16a38558efe15bdb0b15a3d01d7d1', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:25:58'),
('touc8d85caad6ff012457596ed64cd55cab', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 07:09:28'),
('touc8fae33f6d1e3afb46dc607e7069897e', 'itm_1b53dd85ee66f058795ee2971ad55fc8', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:53:26'),
('touc90bcc16e8be4de40c415f4115ed9d4f', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-06 00:03:53'),
('touc92511d5be94063b44741c0b7beaa757', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 11:32:09'),
('touc92a05730ce9e3eef683aa443ebca37a', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 04:23:05'),
('touc9319b1890ac8b05fd3bd325da584d1e', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:13:50'),
('touc9344cfad5076619a57a0c07eb339f32', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'nologinuser', '2019-09-25 23:14:52'),
('touc9347be76245e31a4d4986f0f504ae94', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-07 09:59:09'),
('touc942f43385eef29bb0b1334c95e14903', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:32:07'),
('touc94373efd01e450f19f453f1d532a915', 'itm_7da4b726eeb3a492413789219c83c6b2', 'nologinuser', '2019-07-02 06:02:32'),
('touc96265f4de2859edf761325c6be9ebff', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:02'),
('touc9699800b95fb125f3b5b0271c6217b4', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:45'),
('touc97d3579f47cd3539d037247e7d4e790', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 10:32:15'),
('touc9808139003b349c26dff9b689ce7541', 'itm_fd660e4194cc3c5ee817e94aa01bce5a', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:32:19'),
('touc992284b9416f26ae26a395cc06b0211', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 11:58:54'),
('touc9de140dec3d0c5315215a6c3c685b1a', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 04:15:54'),
('touca071873e3b3021bf5e2f4257dfaa3bb', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-28 12:21:43'),
('touca1ba2a68ee0ca67f5e925fc295e6ad7', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 18:45:51'),
('touca1fecb24325bc242ec542b1eb43ae62', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:35:13'),
('touca23908fa07e729c5507543a5c3aa66b', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 04:17:07'),
('touca34dc1239ab4db2abc308f2f62faaca', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 09:43:50'),
('touca5375bde0d7e33354cf20f2797afd88', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-14 06:47:08'),
('touca5be05d4b757575873a315ce065c867', 'itm_a65be83fb825406c14760f8848ff7bfa', 'nologinuser', '2019-06-28 11:08:34'),
('touca9ae78724d9cde0dc750eb122e0855e', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 08:15:20'),
('toucab6e62f1992d4adba351d8f1996177c', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:17:44'),
('toucadcadab3a9efa826a29ebc23887d008', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-10 11:56:00'),
('toucaddc6c76b2e2af336861b76c617be74', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:29:43'),
('toucb213c99d8f093cabfc7268bbf727e38', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 07:20:21'),
('toucb294b6fc6ee5b5ecc136e1b443af20f', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-11 08:00:21'),
('toucb37630529ccff4e0edeabe47b305e12', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-05 13:24:52'),
('toucb639a93a68b2e9e1aa63a2dfceccac4', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 08:12:13'),
('toucbd2cfe0e46e3dccc93a263872f3cd41', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-14 13:52:27'),
('toucbec1781e5ee3338c4cfb096fdabd3a2', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'nologinuser', '2019-09-10 22:23:22'),
('toucc0de0e11c294b9ba8f3aa840cd6b426', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 21:38:18'),
('toucc4e57e792da8f1441e0ff1042ca74c8', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'nologinuser', '2019-07-03 03:33:29'),
('toucc727bd384437e3ab11427576f096837', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-12 06:04:21'),
('toucc796d11265a228a19c6cc19a15f4588', 'itm_e9584f6420da26e2378adffc455f7431', 'usr786aa1c24816cf370ecd666472d8b548', '2019-10-15 18:01:34'),
('toucc95b03aa2fd708f3fe06dd628e15086', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:31:23'),
('touccba9e444612acd7e822553b33c7638f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:33:28'),
('toucce36ca781d8f2705ba135f54d5fd87c', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:00:37'),
('touccec8606d73586b3bd7e2364ab330865', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:22:40'),
('touccfb79a2ba6524a114a2483355e29be1', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:36:15');
INSERT INTO `bs_touches` (`touch_id`, `item_id`, `user_id`, `added_date`) VALUES
('toucd1a23ea39ee17b0546311d307fef5ab', 'itm_40c6367fee11bc95a223027f129e2bee', 'usr47d6cafdbced2e9935d62d18d2952cc7', '2019-10-03 08:02:55'),
('toucd5ce9b15e0e5639e01c887e2558273c', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'usr8ad862ee8b0eb6495d389cfe6f35655b', '2019-09-14 04:24:04'),
('toucd675b26882879a1eedf899be2121ff5', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 09:43:35'),
('toucd6a59ea49d180bea46f9937b973ef0c', 'itm_d827ad45f61ccd9df35e24c1678ea6c6', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:59:39'),
('toucd7f385e191b4e3d256b26625c0a4cc9', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-17 14:31:20'),
('toucd95c3f777863ea76225e6d29527f53a', 'itm_52c74ad65651f004b6145559b25db4d5', 'nologinuser', '2019-10-05 10:29:01'),
('toucdaf88e929d0b9bc2bf556ce89a9ee33', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:47:52'),
('toucdc726595e3f98e1b2a90d654435e31b', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 04:23:25'),
('toucdcf5af49ac83ae280360837dbb58d3f', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:38:34'),
('toucddc57fb400f7bc254989f267d15afdf', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'nologinuser', '2019-10-01 10:30:49'),
('toucdfacc952a3fcbd2d69f534277956ae2', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-12 16:44:42'),
('touce58a078080252f1efb250afefaa9a26', 'itm_0580d078a65d3887457b4941033ce226', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-03 03:57:43'),
('touce5b435b6122abb78629f28b89b8e764', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:31:00'),
('touce8c00aee4b2ecd34ec47eed1cd4ba86', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 23:03:48'),
('toucee02e341ea3876a22f8f6ac57552c74', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 11:14:41'),
('toucef071d92f2de55fe8bf9bba27f0e788', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-18 21:12:31'),
('toucf17265ba3ccbdaa42caee314044972e', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:07:39'),
('toucf182f76aa01da661d8513cee967e9c1', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:08:57'),
('toucf28fc50290a4c416b554e6d841b0627', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:58'),
('toucf50ffc59942b096826ec942afa0488e', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 08:57:46'),
('toucf7563781c14f0fb126bd2a0237de96a', 'itm_a65be83fb825406c14760f8848ff7bfa', 'nologinuser', '2019-07-01 07:55:43'),
('toucfa14a4d44bf87b91146bad82177ddd9', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 04:01:45'),
('toucfaadf8537de2f773cff99a2e40c54c8', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 05:09:59'),
('toucfcd21ee97850e766fc7b31d006b88cb', 'itm_92994f957013924861ccf81e6a8ea4dc', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-03 03:48:41'),
('toucfeafa0846bbb1c0df967751743aebd8', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-30 03:46:17'),
('toucff144188d816eb39e7316335067f6fc', 'itm_715fee0a325de85667e2271e03d8c30d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-21 03:55:38'),
('toucffa9fa23a508b3067953046350e10fc', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:39:15'),
('toud015147d2c381a022f5c6e7da5da49cb', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:31:18'),
('toud02372fe5b8e6c0dd6fe5cf60aabec04', 'itm_b18cca37df85a01418739145d4369153', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-02 11:45:27'),
('toud04651aeab5aeb5906584fa6b71ff0d8', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:32:30'),
('toud0ea3a5b1d62eec109c484951fc22b04', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-08 16:43:34'),
('toud0ff11a1860c854f707828f53bb14827', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-07-02 07:46:24'),
('toud1186d57fa969a4744822070eca58e33', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:47:11'),
('toud144fa97a152d2109bdfaab46bffb748', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:22:04'),
('toud14c5c9a00d096539deddb337d11bba1', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:15:00'),
('toud1b14ea1f18530a48bcf6304ace170d0', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 08:12:06'),
('toud1b521c252abf41d1f4de40ebe9dbd62', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 10:15:06'),
('toud1efa6868faedfa32c5a77cd3eb688c2', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:24'),
('toud21c3ce8a0844742e6ea4954634023cd', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 07:18:09'),
('toud23bb5ea7d2084c561ebc9629f2d35c2', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usrf31597aca589e0f1b43061cec1f399b2', '2019-10-02 07:13:21'),
('toud2935be52644679e3002d98aabdcda30', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:31:28'),
('toud337a3ae1eb50de6e9aaf8e6c2d61406', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:36'),
('toud35fc386f8d339653c8537729928fd61', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:29'),
('toud3ce156d2321afdadb3bc9044c665d12', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-03 08:28:02'),
('toud3d7bc6326e2842d7298b8680446bc80', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:20:44'),
('toud40eb2066c971dfe435a4b3cce498b2d', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'nologinuser', '2019-06-28 11:38:22'),
('toud41d97ff970b586284edce0907c18e9c', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 10:19:32'),
('toud42167534daab146bb2483b3e7b24ec3', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:05:28'),
('toud424f1f77b9e2d95f16c880ba524c845', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-10 22:00:22'),
('toud4397c35b2089dcbbd96b1f6c196c36b', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-16 04:41:21'),
('toud452d9c16db9a114ae504e42f06da885', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'nologinuser', '2019-09-01 23:07:53'),
('toud457ec043ed38823d969dabffd533b89', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-14 04:22:33'),
('toud46aaf98b625c3a09c016a4c4e638a57', 'itm_7da4b726eeb3a492413789219c83c6b2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:08:02'),
('toud4a903e27e1bce43c0d2ef4b4213a2c6', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usr9bbff91915b2c1a9566b99fbe54c75a4', '2019-10-19 01:12:14'),
('toud4ec6b07755deb965d2223b2ae9daeac', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:54:34'),
('toud4f8b5ba71ce206384fc474640953ae9', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:40'),
('toud512e5050fc394ecbe582afa9b7cef96', 'itm_df00498f0ac018c80372cef801513700', 'nologinuser', '2019-08-28 15:31:45'),
('toud53a1440b136f97b018a1525ea486ecf', 'itm_d827ad45f61ccd9df35e24c1678ea6c6', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-08 07:10:28'),
('toud5426ca05cc79771da92711079634e46', 'itm_92994f957013924861ccf81e6a8ea4dc', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:49:15'),
('toud553e17373d7f4acc32b4af184cb2cef', 'itm_02db30c4e6a968ac1955cba086d95b17', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 15:47:24'),
('toud596c7f522af8b640f28ce0fb77b29f5', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-10-17 11:42:07'),
('toud597f25152d70742fea11cba2d03b203', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:31:10'),
('toud59ca4163334d829b14c86d432d8c1d0', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 05:26:33'),
('toud5b67268910b4a77f2d8d98799215293', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:36:19'),
('toud5b7764667767a11f34739c474a5e956', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 03:52:51'),
('toud5d8d3868a7a9cb808057f2645c1ba31', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:04:41'),
('toud5eeee8bb517dfb4d8c690b0274163e9', 'itm_5058fb44b1f3a2efd9cbd0925ab67785', 'usrc58fe4f5fea11c19ee38cc1de75949ea', '2019-10-07 07:29:44'),
('toud602a201d8cbf7a3b20f99e3d1c0197d', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 11:38:41'),
('toud60fa3e74748ef7ac2efa20de25af2b6', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 11:38:45'),
('toud61ee6ae6244b6f855264a4fbfa9457c', 'itm_33421d314109f37e8cff807cd4149cee', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:48:44'),
('toud61f581da0b971ee4805104712b07d00', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 12:04:20'),
('toud62ee2cc04019db66544931dec5555af', 'itm_52c74ad65651f004b6145559b25db4d5', 'nologinuser', '2019-10-05 10:27:31'),
('toud63a94dc16a3079f001b6506b46ef65c', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-10-14 15:06:55'),
('toud6441fbc6086c911ee3ec0d24b26714c', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-13 09:25:54'),
('toud651a019e3d0e2c31025cf1b2b47bf3d', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:10:19'),
('toud65d87725437616cb27a8d831e2d461c', 'itm_f1864130c55178f6c61cd76350426c40', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:08:37'),
('toud65f29106978cb3c3e71d533def0edcb', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:38:22'),
('toud663203b09be4f2b18739a2cd16e839f', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 17:04:37'),
('toud6a3dbea8c4abb003a9f727f637fb298', 'itm_02db30c4e6a968ac1955cba086d95b17', 'nologinuser', '2019-10-04 10:11:38'),
('toud6c3009dbe40ec47234e390a13b265a8', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 11:39:53'),
('toud6e602b7a38594fbf61066be17c821ed', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-27 10:22:55'),
('toud6f729cc73a8d142197c6c53fbc86c65', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 12:01:59'),
('toud74ef65e3f74f3ee4d6c044328271f11', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 03:29:56'),
('toud74f7c261b5c7ad1212302f58de2a024', 'itm_45f53a2e646e484d6037e562f4233cfa', 'nologinuser', '2019-09-12 06:38:31'),
('toud78662a0160575650ad583235861b813', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:34:57'),
('toud7a53ea69c1be381d96c3509d1c91281', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-08 14:15:50'),
('toud7cc3a74b40ace343d52507be48ff76e', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 08:22:43'),
('toud7df57eab48a1ca0c3d9dfa7eaac97a9', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 09:59:28'),
('toud7eeeb2bae6b3f18c293f8c6e4e92aec', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:20:36'),
('toud815a6275ccfa7e3e3c41d6484a76616', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usrf75bd4e3f445c1f676b725b971d77084', '2019-09-09 15:52:41'),
('toud823944760083a456c7df012fb207267', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrd6c16616f5c5a93fb7a6ec4294cc9f19', '2019-09-09 10:57:06'),
('toud8253126e441c64c9eb42cfcd688e4ad', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr706a71c3e5bb7b0f5297fc0ec706a61f', '2019-10-04 08:53:46'),
('toud8371368681f9aea9ad2a9e2106d6ffc', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:53'),
('toud8378aa0c400b1dc675b00d5051032ce', 'itm_45f53a2e646e484d6037e562f4233cfa', 'nologinuser', '2019-09-12 07:39:35'),
('toud84cd090b254d3c348dc4eb754dcb7b2', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 05:31:40'),
('toud86374a33296d82a75c7b0ec5bb8f0eb', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:13:10'),
('toud86dccca88e32b04d9c9a58dcda60ccc', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:22:48'),
('toud87085d11407abcdac1bfe4e7d9c869d', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 08:58:20'),
('toud879d239c8992e015c3226fa50886fc2', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usrf75bd4e3f445c1f676b725b971d77084', '2019-09-09 15:52:43'),
('toud87affcff38ba39e92ddfac9b3003c18', 'itm_52c74ad65651f004b6145559b25db4d5', 'nologinuser', '2019-10-07 03:10:54'),
('toud8bc1631764f167e80d9d3a724d181b1', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-09-02 06:33:56'),
('toud8df3ff3c85cbe975c80b5eff323b1f3', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'nologinuser', '2019-10-01 06:49:47'),
('toud8ef91f7f5c1c3e1f06ac609dbe7fb21', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-22 12:07:06'),
('toud8f9ea8224d01a74074704d5d3356133', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr802e5a133a8caaf0c428fa88c6bc9eb6', '2019-09-11 19:01:34'),
('toud90a294d50ce4506bf87fd428c2f3c3e', 'itm_709970eef73e104ca5d4114b2485355d', 'nologinuser', '2019-09-21 04:12:56'),
('toud99915fd3068478e034708de8f8d0747', 'itm_016531de690206f9e03cf036c334a467', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:23:00'),
('toud9a78c4c7c006974f61ed88f828e135a', 'itm_e9584f6420da26e2378adffc455f7431', 'usr786aa1c24816cf370ecd666472d8b548', '2019-10-15 17:53:51'),
('toud9c53f07063b7a2aa570d196a4c6e36b', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 16:25:48'),
('toud9d677400aac43bb24d4f77048c9142e', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:44:49'),
('toud9e2879c10af575f7b7dc218376df043', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:15:24'),
('toud9ff413ef3b685f5c401c2dce1ef0095', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:43:19'),
('touda1056f805c41a958182518880802055', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 17:04:22'),
('touda3c50c9b459f0325a6d1c86c09d6343', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:11'),
('touda43bc7c9a1f2b7f1f5a607748b25fa7', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:09:52'),
('touda53189594d7292df9ad84b91a3ce4b6', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:20:14'),
('touda5fb643e97c30814da5d370d91ed7c5', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:20:07'),
('toudac2f619e9e76788c82961f05c179f54', 'itm_228aca47caf96513d300aba7aa9f2fbb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 11:14:49'),
('toudaf226dfbc14da9d19f34e21333efcf4', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-14 09:11:19'),
('toudb1dda70270c5f643c0228d96eeadb69', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr4edb805ca791dc7677ba4a785873debd', '2019-08-28 12:12:53'),
('toudb444e94fcc3071cbcb2453cf17cd5cc', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-03 05:50:30'),
('toudb529df61d957ed5c3b3d886fe3758ea', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:45:42'),
('toudb5f92e0de9051d6772c3e2826d8dd33', 'itm_81e1b4c39551a5765743662e1f5f079b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 16:25:00'),
('toudb795b42e1f97e2e8c27b764f393e187', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 06:46:14'),
('toudb7e54f1087cc66f73b1d4f08dd67827', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:40:50'),
('toudb7f871893b0b022f67abcf9915894c0', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:36:56'),
('toudb9944124922c52b84e947bbab2e53c2', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:05:23'),
('toudbaa1bd9073dcb0426663caac3da4fa1', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:11:10'),
('toudbd81e686688e626ca2d54a659a761ab', 'itm_a65be83fb825406c14760f8848ff7bfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:26:13'),
('toudbfbbf394f916129b4d88e059b13b67d', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 10:24:57'),
('toudc095b634ed02265c0a4a648775ebc05', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:09:48'),
('toudc312fcb51564564b272a8d51a2e31c6', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:09:32'),
('toudc531a64a6eea36676c717e7212ce156', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:13:10'),
('toudccc343bec8df4bf092549142a6e6c36', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr8f466efaba2c555c63f6b739db4f6f6d', '2019-10-04 09:07:39'),
('toudcdaecf8e8d3afa65de730dd05610b71', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 10:53:47'),
('toudce97697f9e38f2b47044e312a19240a', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'usr84264292f0b3360143f6ea8776b77f20', '2019-07-24 07:32:49'),
('toudd732f2cbfeda2971a9b7f331a259f3e', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:59:21'),
('toudd84dfd0c480129a3b6965f0d61ea09f', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:19'),
('toudd9e641d6c8116c22594ec91af801e07', 'itm_ba0837310e18f29408f09fca748271ef', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:21:18'),
('touddada870136ffbea4f6e867fc62ab424', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:35:09'),
('touddf243df9b3aba228f7407d00c644d1c', 'itm_941519b2438b0747386a6fee39e5b279', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 10:54:19'),
('toude0716f623cf7736bd22ceb460421752', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:29:18'),
('toude41ba424288e78c59697e46146114eb', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:53:27'),
('toude5b017f6f81a271cfd2412f286fdd6a', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:33:06'),
('toude5f8d1b44f647596daab653ae666d63', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 22:05:14'),
('toude9c999e9f2bbe4fd1ee1ff9df922653', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:09:42'),
('toudeb809f87746d75ebed8627570ab1fb9', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'nologinuser', '2019-09-04 08:52:40'),
('toudee557417112de903080cc9c16aaa45c', 'itm_d2cbc2f461e291a45c6b97001902d1f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:17:45'),
('toudf119b556d86615cca67dee006ed798f', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 07:02:48'),
('toudf195e2a29f6633718a4b0630c7e8688', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 09:13:36'),
('toudf73434557ea9bb062dd8164752dfbd0', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:09:11'),
('toudf9c7e0a6e6cdc8f4f2e8a77e74781cb', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:09:46'),
('toudfd5c87b89450f81863af4e73861e47d', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:02:50'),
('toudff50326ef3fa0050ba06f6aab5fd944', 'itm_b8862a5f5afad05aaaef6c854e95b593', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 03:43:32'),
('toue010b48b6c0f0ddbb44e91b5b281845d', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 22:09:59'),
('toue0c56a23562cb4b326cf24615ed2f376', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:13:38'),
('toue0d32b1feae2569bb09c5a6637847a4f', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-10-03 05:37:08'),
('toue0dc74782911cd4faf265a871c463a24', 'itm_d827ad45f61ccd9df35e24c1678ea6c6', 'nologinuser', '2019-07-08 06:40:10'),
('toue13c60aaa116a9ce711fe99bbbd4b3cc', 'itm_feac64d55d1f019e3038201291c441f7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 07:42:59'),
('toue14572c13a6d8c8c3cffc66b24fffd7d', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'usrba287c1864e60a6def65e8fe3242d906', '2019-09-11 05:16:22'),
('toue1590828e8874db5742684e7605a5876', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:48'),
('toue16c4caa7d5e4e33029b6ccc2b5c46ec', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:12:23'),
('toue172be9e0321b4abdafa4544c1d53b38', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:56'),
('toue178c6fe0414fe7b9151f0b61127bea2', 'itm_d0a9c17f4d478bdc77453cb816c135f9', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:20:26'),
('toue1c9f22067a393a2778ac6639f774225', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-02 10:10:07'),
('toue1cc2393028aa410d96dc4c5a4520008', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-28 12:21:47'),
('toue1dba08a2b4b7c0add51a8f23c861457', 'itm_aec300faa4929e3342679e55e63c24bc', 'nologinuser', '2019-09-28 04:17:20'),
('toue1f0443296b07436281b267a124502c9', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 11:12:18'),
('toue1f3b710e8b9da1b1283889265b524fc', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'nologinuser', '2019-10-06 11:57:58'),
('toue202bb62cbee7038e1c6cb73c794df36', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-24 09:27:07'),
('toue22dde30079d118131f5da17045c1881', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:30:51'),
('toue2479695b431b6b83653a77a132e2ab5', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 12:03:14'),
('toue2744d3cd12ab33f7be53f09a5822e7a', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:02:25'),
('toue2ba9aac534e43ae6c4a63f44b12a348', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:04'),
('toue2c04414f4eddfbb32ccec6319cb2ee0', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:23:54'),
('toue2e7b610444de303c750b93b8abd8fd4', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-08-29 18:43:59'),
('toue2f16833becefc9636544baa3299bed1', 'itm_19d8a632ca279d2a4739913172cb71dd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-16 04:48:31'),
('toue2f8d24a81fc97ae20a64ab54622a180', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:07:51'),
('toue2fdf35194b482db4c3041b0353a7c63', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-13 05:25:38'),
('toue3174e6dc299762f586b094fbc8d6e04', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-17 14:15:35'),
('toue349824160837fb4d173938bd19902c9', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 19:19:56'),
('toue367b862f947ad0b7bc222e9999b14ef', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 04:01:20'),
('toue36ff9e6026143eb6bc04fd4f65afe94', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 10:28:45'),
('toue3c021e31afb27f8c55ac42910f8b375', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 10:24:13'),
('toue3f32f2a63aadc60c10946d23bc5a19c', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-02 05:44:36'),
('toue404660b1020b6a816cb0457ae8c25e7', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 09:44:10'),
('toue420d14760f6ecf802b84fd7bce847b2', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 03:52:23'),
('toue4571a8b8b226c704d536ef1670dac1b', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'usr9d4ff8cc99cace4e30dfe9e64de0d64c', '2019-09-13 22:16:38'),
('toue45c6ff086649af8940331c06f83a5ba', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 11:00:29'),
('toue463db6494b3061700a6d6c1425e9067', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 23:06:09'),
('toue46ed7b475e3ab87aa239cc462cfd5cf', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'usr706a71c3e5bb7b0f5297fc0ec706a61f', '2019-10-04 08:54:46'),
('toue47d8994eb5aba0be49ba7958fad093e', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 09:44:36'),
('toue49374dabaf2ef16f6d97daafde72436', 'itm_f435477e7d52baac496a0be7621974c5', 'nologinuser', '2019-07-01 09:52:38'),
('toue4ca3b75c330227acee51da53aaa9678', 'itm_ba0837310e18f29408f09fca748271ef', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 09:44:53'),
('toue4f1f056011bbf3bc6acf5296848d7c8', 'itm_02db30c4e6a968ac1955cba086d95b17', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 13:54:07'),
('toue4fc388d629f5e5b6ded98442ba06a61', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 10:49:59'),
('toue51f11f282678a801d5b57fd2158c1bf', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-10-03 03:30:26'),
('toue54fea4ac21235fd3575fdcd89e1284b', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'usr0af0a9e866854e1205b104df27fcb0da', '2019-08-30 08:58:58'),
('toue58927b8a5e000f3138219ce47b4d0a7', 'itm_aec300faa4929e3342679e55e63c24bc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-29 10:43:12'),
('toue58d60de3c08726c9d458763c40cd473', 'itm_3574960a0f206a81307cfea2804acbed', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 13:26:59'),
('toue5ccc51864fe748d289ebcba73330bce', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:50:39'),
('toue5cee37dfd0310f3b622c3e688de8f97', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:06:45'),
('toue5f197429b1ac8254c9b12568ec1db81', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 07:42:56'),
('toue605a64888ae41310a0140b51bd3de71', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-01 23:07:36'),
('toue60d51f2cfb300de101d4bae7a8c7f61', 'itm_33421d314109f37e8cff807cd4149cee', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:54:53'),
('toue614a1f5c85ea27d40892ba366db9220', 'itm_54709837e70ae2f179b49a739ded54d7', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 16:26:14'),
('toue63937f6d3b8130e1fee6a1ae823acc9', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-14 09:11:12'),
('toue6612b1aad5ad945c093ec357135c394', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 07:05:05'),
('toue6878b9ba76d8f3fd60e8f678e47b5c3', 'itm_d827ad45f61ccd9df35e24c1678ea6c6', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-08 06:41:30'),
('toue69d3285f82067ce7b9ef11c3d3cdbc1', 'itm_aec300faa4929e3342679e55e63c24bc', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:27:15'),
('toue6ac16da66b4af142e612f98acfc89eb', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 08:00:05'),
('toue6ba628c3b9f7020cacaa8592fe9ea27', 'itm_519b7a8c4c4d29e4130548481ca6f9f1', 'nologinuser', '2019-07-02 09:50:06'),
('toue6ccc2562837ae5e5ad1e3678ae1972d', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:42:11'),
('toue705521beeb7e70dcd6e2cd37c616cc3', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usr4edb805ca791dc7677ba4a785873debd', '2019-07-24 07:50:27'),
('toue71dddcce1d3c885c44fef2010502e36', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 11:24:20'),
('toue7253a65b1e324f5b8cb1c908c2b9ddc', 'itm_3a197e7a07668e4d8580e5b129957107', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 04:35:18'),
('toue74fa1c7804e7d7b1755a58bd363aced', 'itm_aec300faa4929e3342679e55e63c24bc', 'nologinuser', '2019-09-12 16:20:28'),
('toue79d59ac664ac165ef8d0ed6bbe6acec', 'itm_aec300faa4929e3342679e55e63c24bc', 'nologinuser', '2019-08-28 12:29:45'),
('toue7a6ffda7eba5b7f02b532d104fed728', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 03:48:52'),
('toue7e19916d8062c1afea8b373d183ad10', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usr3ef9bac5ec67dac879f099d0faf16d84', '2019-09-04 23:02:05'),
('toue7f6248e714d81aafd2c30f62f0cb8a1', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:59:09'),
('toue8323203a21541e12b644a12c67cd3e9', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 08:15:36'),
('toue86b41d801375eac8348f18a66ff29f6', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-10 22:31:55'),
('toue883c0d4ae52b09836f28885e0414234', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:07:05'),
('toue88cf65450d7008eb2852551dc8439f5', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 07:38:17'),
('toue8bb209b7c9b99d61654bd57b92b6e09', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 11:29:57'),
('toue8d3645c2f6490f36f8f22be586bdd43', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-30 03:05:14'),
('toue8d47aaf2b15a4414acd58833be69058', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:13:27'),
('toue8ed22f14f3ac021691db2a2178914fe', 'itm_a8480fc3cee37bca370eddd16cc5c7e1', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-03 08:09:32'),
('toue901f0176fa21236b7b7ab985f7f9da9', 'itm_d226e2115d5632e9aaaac89bd880737d', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:55:13'),
('toue90a017073df340203389f7e27319443', 'itm_a0ee873a872f79828676b8850c3e875a', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:35:16'),
('toue90be6c70d88bb3a5bf0b3b5b9bf5582', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-09 10:25:50'),
('toue94311710ba5ec040788e08c92603a5b', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:01'),
('toue943ad8b45e4ee88776b6e52c033030a', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-11 06:06:48'),
('toue955f044c524a383b0df27c0fb08ab58', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:33:53'),
('toue96fba2bbe77b18bb2a147c7ddc33864', 'itm_30cfe8731b915ba7d3250af1ce8d6247', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 09:44:50'),
('toue98fc3eaa688417d3c79110d60feb218', 'itm_10c058734f4608bb3b1b2c3b8a7853cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-15 17:25:49'),
('toue99d20f6c809301edd2e8ca6f2830aae', 'itm_715fee0a325de85667e2271e03d8c30d', 'nologinuser', '2019-10-12 14:07:18'),
('toue9b0d7df2e8b34899a6f0751399c027a', 'itm_941519b2438b0747386a6fee39e5b279', 'nologinuser', '2019-06-28 10:30:16'),
('toue9cd8cd7ccc8c9bf2fc037be616b3dca', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:30:35'),
('toue9e6f4c21d40565631fe38185a4cd984', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 10:26:09'),
('toue9f5e2615197565e46fd27dfbbd2dc83', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 10:19:46'),
('touea0d70a86bfb8acd6e3c5336579ef168', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:41'),
('touea12ba81a9a96b74484b2486f4d691e4', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 16:16:01'),
('touea38bbf3556967e1fa4e047f30c7dc98', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-17 19:16:48'),
('touea3b1a97758aeb190e8a4ab685c49604', 'itm_48d13d3dff6e736f8c5fb5f71865ad6f', 'nologinuser', '2019-07-01 06:20:58'),
('touea4c37c87f03846e482be8bb64c748f9', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:31:33'),
('touea51b461073be52942e3a936e7fb5045', 'itm_b18cca37df85a01418739145d4369153', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:53:14'),
('touea5b9260d3c280617c2f12fe56d82497', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-13 11:21:02'),
('touea6cd60ee61212b0f3c6d7070f7f393b', 'itm_feba75af4918ebee809bafb3c5e4c963', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 06:53:54'),
('touea8acb2da90bf38ccc2e2c20c9572002', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:35:49'),
('toueacf4b7bfa069d0609e079742b2fc7e0', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'nologinuser', '2019-09-11 09:05:42'),
('touead20141e9c9664bb2e9a187acdb578c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-22 09:28:47'),
('toueaee6d9b2d3617c14a71a69aa67d0464', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 08:09:41'),
('toueb0b9358fa8a84fb58fd8c26b7e18d6f', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 08:12:09'),
('toueb1112e2069f44a9edf0cb6875fe438e', 'itm_b4ad3905b576abaf702d491089bfd7fa', 'nologinuser', '2019-10-10 08:59:43'),
('toueb59abd4e4217993fbcf63bbeade69a3', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:01:14'),
('toueb7f7e176a77fa3c28ea7895fbe03e78', 'itm_528c99e1abd619f814f246f0daf3f734', 'usr9c7a5d7887d53caba5ba4d94bf1b45d9', '2019-09-08 01:55:24'),
('toueb9698a6b98bb4b4e11ea0f94f0b9066', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:00'),
('touebc4895b8f9d4db1b792bf988b39320d', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'nologinuser', '2019-09-12 16:03:08'),
('touec133c7e82e079923d78ddfb54e26ca5', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-24 08:19:01'),
('touec2013b430ae835627f8bcc7519e61ed', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:58:47'),
('touec2d0232b77217d8ed63a0b8065d4e27', 'itm_3574960a0f206a81307cfea2804acbed', 'nologinuser', '2019-08-30 06:13:24'),
('touec40110699e9f4f3a8c73a372468e6a6', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 08:34:42'),
('touec4f9793a4defb3413190ee12d83f960', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 12:12:01'),
('touec8eb2807d6c7568787141c6dd732528', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 03:51:46'),
('touecbae480b75b1d5fcf2c805cfcb380fc', 'itm_33421d314109f37e8cff807cd4149cee', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:55:56'),
('toued1e9e00aa5ceb961aad2f6e50396ca9', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-03 07:48:25'),
('toued245ada6229d215a101525b42fc132e', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-14 06:43:09'),
('toued4cc2ce84f77bfbb51b052e437c44d0', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr9c8be3fbd3f169f5015105a9c44f7d30', '2019-10-02 08:08:25'),
('toued50e00ef17b8a20549780cc498e4f0f', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:16:30'),
('toued599f1a213b930552900ecea9dcf70c', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 07:53:31'),
('toued68fdbfa9e92817b4ee267afabb0564', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr9c8be3fbd3f169f5015105a9c44f7d30', '2019-10-02 08:06:01'),
('toued6eaeb7f5f92c031f414f0e8930631f', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'usr783ee28d531229420e5cf3ba6fe49da4', '2019-09-08 14:54:14'),
('touedcb664e96ffc67ba7ea56b98ca4cbaf', 'itm_caf91ce095ca25085c5f89a45a7cdd65', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 06:06:14'),
('touedcc9c3eb77a806e2c6bddd570a5aeac', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-28 03:51:45'),
('touedd7e49ffc5fb09de59bce881636db7f', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:12:31'),
('toueddf1a157356ce1289147fb8da438b5e', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-12 16:52:02'),
('touee0692343bb5c5acbdb5563d46a82fbb', 'itm_dfcc63f165e4b4deea55b667b0505690', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 12:32:14'),
('touee0b0cfdb7e41b947992b5a39c8ed4a3', 'itm_d827ad45f61ccd9df35e24c1678ea6c6', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-08 05:29:21'),
('touee1f111ad4b58f3a45211a882e768eee', 'itm_715fee0a325de85667e2271e03d8c30d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-30 13:36:32'),
('touee2e3e6cd387a41c89ffdb691de47cf0', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-01 07:01:43'),
('touee88e9536ec0e413c268f529b897cc2c', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:21:41'),
('toueeafbabc989bdd90657f14035e80b70c', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-06 05:00:53'),
('toueec4a0c0efd74b214a40d95da8e70d50', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:29:45'),
('toueec8e05948623ac274fda53e223f05ec', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 05:07:31'),
('toueecb54dba25728ffb3317404537ddc68', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 10:32:38'),
('toueeec8507deba3c932afeb8cc261bd099', 'itm_3a197e7a07668e4d8580e5b129957107', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 12:16:43'),
('toueef1c3e9aea0461dbcc084a92d6f9fad', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-13 11:15:25'),
('touef02d9228475497f5a89bce3f9aca595', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-10 06:08:41'),
('touef0484b8ca40ae0677fae86014842a5b', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:28:50'),
('touef0f6c5141c0cf21189dfb21ef344aaf', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-27 22:50:40'),
('touef65aaf06593101b556a32ee28ab7e27', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:41:58'),
('touef7e504a81a4975b276c02904eee934e', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:09:43'),
('touef8feec9b52300a116ccff4c3935131c', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:20:18'),
('touef92e22123e94544cd28e0c990c2ee30', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-18 03:52:47'),
('touef9e25b6f0c532f577f4ae623767c88d', 'itm_52c74ad65651f004b6145559b25db4d5', 'nologinuser', '2019-10-07 04:03:23'),
('touefa1b7d8932e1213538d654ec98f6e98', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:21'),
('touefa6ebef432eb741a4e7fbec698c369c', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 03:58:17'),
('touefaed6625ab40b9a198060d56df5affe', 'itm_f435477e7d52baac496a0be7621974c5', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-01 12:06:07'),
('touefcc336696500e93ece7c8656f056856', 'itm_0500dcce80c7d70b79453a8ff4096428', 'nologinuser', '2019-10-07 10:06:06'),
('touefed74216ea04fa3686d6ffaa1828a04', 'itm_228aca47caf96513d300aba7aa9f2fbb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 10:44:11'),
('touf017746bec1801c6339dcf881ad18712', 'itm_528c99e1abd619f814f246f0daf3f734', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-30 13:39:38'),
('touf019acec8f289fdc9c8080a80e408815', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-19 10:41:50'),
('touf01b75ce92e595ef2fd74482a1d7c7f3', 'itm_aced66ac6cdc6e1d46c76e08b725802f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:49:20'),
('touf01c5e8bacbf68350815d36ab5085bed', 'itm_65b0d9006e805ab13d727f6b2e06af6d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:10:53'),
('touf063166280a52c24b5da1c17bc5b0470', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'nologinuser', '2019-06-28 09:17:09'),
('touf083218813b8d39a4cf78c45f56a5c6b', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:14:36'),
('touf0868256e31dbb1318ea7d21fbfcabc3', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-24 04:53:53'),
('touf0b985de0f6937e0837cb6435d7e17c1', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:41:35'),
('touf0bd7c9326c26d2240680afe2c490442', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:06:52'),
('touf0dbda0b35384f4279422ef7af34a3ab', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'nologinuser', '2019-09-02 10:28:10'),
('touf0f75b7378c7c9c03af2becdabe54f58', 'itm_dfcc63f165e4b4deea55b667b0505690', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-01 03:29:45'),
('touf1135a26b7052964352a1ec170109217', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 11:36:27'),
('touf1ade480ef73979c2cb2f2305f07dbfb', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 16:30:11'),
('touf1b45d70f4c835362a25d2ac1dd41f59', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'nologinuser', '2019-07-01 03:30:29'),
('touf1dc06fe746e3adeb4282cf0c2154366', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 09:47:47'),
('touf1dc8e7329809e40d0b4803c1942fecc', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 04:10:53'),
('touf1e0e025d288b1eb9bb105db91fa6394', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-06 11:37:47'),
('touf23597b1958912cbf6f9385b29d199b8', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr00220e8412366abd4f57f501fe385085', '2019-09-04 05:10:48'),
('touf24b6b5bc1f4e3ee62ffce3f811dfc9f', 'itm_a88e7b941c622da8ea94af30f6147997', 'nologinuser', '2019-10-11 20:54:32'),
('touf260c585410548bfc3adb5defca2f8d3', 'itm_adcfbc758e3defa6ea7ed41dd01287a8', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 10:02:57'),
('touf281b3ea722524bde99f1184b3d5933b', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'nologinuser', '2019-09-12 17:18:02'),
('touf287d659a988dfb70aecc2fe7bace2f2', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usrf75bd4e3f445c1f676b725b971d77084', '2019-09-09 17:05:31'),
('touf2d34aa5ba7b59f5335816c699ab6562', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-15 16:22:01'),
('touf30c0fd77a226ebd6dcbeb4c38e2bbaa', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-18 11:29:29'),
('touf329d00999197e0916a0efcb4bc64917', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-14 04:03:32'),
('touf32b15578cbb39c99738e0469d975146', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-01 23:10:01'),
('touf35e283d7457b631aa2dab7a47580ec6', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-02 10:26:52'),
('touf377b6a692a48d126e15ca6bb21bf53f', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 23:37:33'),
('touf37ba6f41df65949b08748e00fa0ba50', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'nologinuser', '2019-10-14 13:53:10'),
('touf3852752d1a864c02f4f7d5e18eaae9a', 'itm_16dfe2dd5f03b37b053e18cf60eefe32', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-04 04:24:06'),
('touf385c2a6560e4a791a6dd5ecb2d12086', 'itm_b18cca37df85a01418739145d4369153', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:51:30'),
('touf3c0d1f852758ec110bee89813515e87', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usra8a5f6f70e6413e3100ca5c4757ea9d4', '2019-08-30 07:20:51'),
('touf3c39c4ab38a9260ec9b049c289910c9', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-07 09:59:00'),
('touf3cd3ba107445abfe555e9f80a5d9d5f', 'itm_53e724271a6494108e270b923a2e8408', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 00:29:13'),
('touf3df135ad5b7e1d07b4c31a71ab16031', 'itm_45f53a2e646e484d6037e562f4233cfa', 'nologinuser', '2019-09-18 03:52:50'),
('touf3e5a71ab46c0783e0b8ea9ffefcf870', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 22:40:41'),
('touf41d95d99cbf0872aca46571f688dbf0', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 03:43:59'),
('touf4233552cfcde00245e899341ca8a9db', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 06:45:09'),
('touf42edfe7676e2c8778615d9c681bbc40', 'itm_7da4b726eeb3a492413789219c83c6b2', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:51:58'),
('touf4449f5b60feaf4ae07d19bee8bee959', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:14:50'),
('touf45b131dd6f231128317e2cd70c6cb84', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-08 16:43:02'),
('touf48558dc8bab9e99a6dfa045515ba252', 'itm_3574960a0f206a81307cfea2804acbed', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:03:44'),
('touf48ffa326927e4fbe10cadabfd5784a4', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 04:32:42'),
('touf4954c13bbb14f8dccbdc4b95ecdb9a6', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'nologinuser', '2019-06-24 05:30:46'),
('touf4a39cdff0349a4fdd22647bf0906b5f', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 07:49:15'),
('touf4a7a096048f7c9153569799a11856db', 'itm_a65be83fb825406c14760f8848ff7bfa', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 08:25:42'),
('touf4b3d719d20336f09577450b6a846f4a', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-12 16:04:41'),
('touf4b8c88c0004deaec7ffb53080e8aadb', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:41:48'),
('touf4f60c61df09e39a61f59bc2c608cb52', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-15 16:12:20'),
('touf51393f1dd09b462145b0f5bb0335b64', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'nologinuser', '2019-09-17 14:23:52'),
('touf51eb558e94b33b34537bd74f76ffaf2', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'usra2f9746d35f1798921a1474b6778a53e', '2019-09-04 05:29:55'),
('touf529d72e9d94a03ccfa2e0beb152c595', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-14 16:21:38'),
('touf54a6a7d8392363d8834eb250a98addf', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-10 22:30:30'),
('touf579abb15fd3b665167a2190993d3731', 'itm_b6301367c04ff6f7e4f1fa6cdaecde0a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:18:30'),
('touf5805fdcb3df2d519b48d68f3414724a', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:34:25'),
('touf5a3a1e31e1e4fa64da6e22e2af54338', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:31:07'),
('touf5ccbf5175ba707ce72a7971d99e3dbb', 'itm_33a66429f209514b7fd2c128fbf1f88d', 'nologinuser', '2019-10-04 10:17:08'),
('touf5d214748791cc2bb2e642722a3fe7de', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-08 07:48:25'),
('touf5efe04fe5c55a04d21adc76b856da9d', 'itm_ba0837310e18f29408f09fca748271ef', 'usr8b51d3a5612079dfd72cc2236da61b6f', '2019-09-12 03:47:41'),
('touf620dc089297c772d935bd207ff40425', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-25 11:32:02'),
('touf669ec840e1d738d06b3d88235a0dc7f', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 10:24:21'),
('touf6e9eb687717fdb33a176df57979b5a6', 'itm_b18cca37df85a01418739145d4369153', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-03 08:53:18'),
('touf6f2704c6eea4faab8331c8dea896fe3', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-08-14 04:33:34'),
('touf701928a81e9cea503e3b02a764ecdc2', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-17 04:51:00'),
('touf71d81d81029c5a47be806de189b1c8a', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-09-11 08:00:35'),
('touf722321f3f0bfba9c5b73b8dd7b6174f', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-18 09:42:33'),
('touf73ce6e74146cd92b5793b813471b519', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'usr706a71c3e5bb7b0f5297fc0ec706a61f', '2019-10-04 08:51:45'),
('touf7413d7bf96845b453137e1dd9219f9d', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'nologinuser', '2019-08-30 03:56:05'),
('touf7446c68c61a54dcf713ffe9c859341b', 'itm_54bbc74813945af6146b97de97927c7d', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:34:36'),
('touf748109f7f017fc4fb5de26fd10e6a89', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:37:38'),
('touf770808797c3c36972fde3637e8b18ff', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'usre42ae5690b8a7e0140a762d458bc803e', '2019-09-02 07:39:46'),
('touf778f6a0f1305de91a5001234b14edf6', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-02 12:16:18'),
('touf798e51d8f32ac087453000fdb9a0a3c', 'itm_5c699422d2b4e2ab32b09afa35597b55', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:00:24'),
('touf7f34bb149f848025a24da11475a1440', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-10-01 09:41:49');
INSERT INTO `bs_touches` (`touch_id`, `item_id`, `user_id`, `added_date`) VALUES
('touf7f516a30b5823e5fb1603268506b5ec', 'itm_3574960a0f206a81307cfea2804acbed', 'usr286d3f370157ccd4e1690153758cbef8', '2019-09-02 06:41:56'),
('touf8254861922177425c8c4dce1732f43f', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-22 10:20:22'),
('touf8282e98966e2ef052a255824122d333', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-06-24 09:04:07'),
('touf82a6dd93313e5ca511b22f947bc2071', 'itm_b4f9fddb9b6f3a6bb0f8baaef7da4967', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-22 12:34:17'),
('touf84c1cd0e9290d20ae430b997a0196ae', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 04:43:01'),
('touf854992abfb032dc00038d3093176bdd', 'itm_a0ee873a872f79828676b8850c3e875a', 'nologinuser', '2019-08-29 18:44:10'),
('touf883c78ac18e283d0100ec8934a7f267', 'itm_bfd764c6d3b307679d64c24525ca85c8', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 20:33:24'),
('touf898df54bc903e7bbfb0d13e5adcd7eb', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-17 14:15:30'),
('touf8b419c0f82cebac877e2c759755e746', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'nologinuser', '2019-10-03 07:48:51'),
('touf8bbfd2f493ec8f7d21fb2b33f0aae9e', 'itm_33421d314109f37e8cff807cd4149cee', 'nologinuser', '2019-09-04 13:50:15'),
('touf8ee39e236c403ef04922f58d5ba7bff', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-09-12 06:17:27'),
('touf917393585f07ffb1685242f9e0e704b', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'nologinuser', '2019-10-14 08:18:05'),
('touf91a24c837392e57d417347d0ee97037', 'itm_19d8a632ca279d2a4739913172cb71dd', 'nologinuser', '2019-09-10 21:13:48'),
('touf984176949ba277f64c2cc47cc2cd6a2', 'itm_8277fd5c824ab26ec25f902c7026a40c', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-07 07:10:35'),
('touf992e404f145defc50aec694f4b2611b', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-05 13:26:10'),
('touf9afece5a7e1a81ec962d29949c2b7fd', 'itm_528c99e1abd619f814f246f0daf3f734', 'nologinuser', '2019-09-11 09:05:03'),
('toufa32def37bbcf4723307c0578ea2d1e1', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'usr2cffd5e8421a8f430086ffd67be7e693', '2019-10-01 05:12:29'),
('toufa5ea804eefc85bcece37b44cfb89217', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 11:23:39'),
('toufa7e4d868a5008f23a7582b9583a2960', 'itm_e8a3f1402c52c8f21844305f2739fd7b', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-12 06:11:10'),
('toufa905f51210a9b868023d7d9a6cd2867', 'itm_29bcfbdba70315bb26cfb39c89cb06fb', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-06-24 08:58:04'),
('toufa9383abfec821e81b445f136ad5000f', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-09-23 16:24:07'),
('toufa9ac86fccafc5057c8576e570f7209b', 'itm_45f53a2e646e484d6037e562f4233cfa', 'usrf75bd4e3f445c1f676b725b971d77084', '2019-09-09 17:05:28'),
('toufabd57626fbb6f0ba573ff75950fb7e2', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:33:58'),
('toufadce3d506357fbb0109ac2bf30a26ce', 'itm_cd86448707196d5f0ea6b555e0da6561', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 05:10:22'),
('toufb4ac0a9b257b1e1078bf50181e2dc14', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-11 19:42:28'),
('toufb575161a97d9c55101b29c8723ddb12', 'itm_d688553dcae9083a4104754662ec4d26', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 21:22:34'),
('toufb7102674a727ffe9eb17223d2f91bea', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'nologinuser', '2019-10-13 11:03:10'),
('toufbdb827b37b51d595c052e9110c98a97', 'itm_02db30c4e6a968ac1955cba086d95b17', 'usrc3c396029e03024b80791b1886df7fb2', '2019-09-09 14:34:05'),
('toufc0c41ecfef70b87ed6c9d9feb48c1b1', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:01:31'),
('toufc195a009a8c8c6fa9a300cf527e8d6e', 'itm_cd86448707196d5f0ea6b555e0da6561', 'nologinuser', '2019-08-14 04:03:24'),
('toufc2e1d5c3138372f9e53ef04b19f1a88', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'usr8dad6e9376480737a9a725e5dab8c402', '2019-12-12 07:03:37'),
('toufc3234bf29b344ce08dd4c5cb09157c3', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:08:08'),
('toufc3e68c7489131ea11f83969661d57e6', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 05:04:30'),
('toufc44e6dd98327b9f86b0af1130f751e8', 'itm_dfcc63f165e4b4deea55b667b0505690', 'nologinuser', '2019-06-28 10:13:19'),
('toufc5ef519405e2fcb2d1b77721e5e3b54', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'nologinuser', '2019-09-10 22:32:08'),
('toufcd045e7227bcdec327a9486f20bd431', 'itm_7372fee97ee9a785a2567fd56187f4cb', 'usre26efc635ee6322368569f1b61b0640e', '2019-07-02 03:33:48'),
('toufceb3005072b820dce8ff04c42e6663c', 'itm_1b53dd85ee66f058795ee2971ad55fc8', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 09:49:36'),
('toufceb7ae78c4a1ff989f99f3aab6990e2', 'itm_90d42bee3eca1658c9c2f6da0e1674de', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-02 06:11:06'),
('toufd64578f7a0f891ec0898632599ce463', 'itm_3574960a0f206a81307cfea2804acbed', 'nologinuser', '2019-08-30 07:45:03'),
('toufd7eba5b6bba8e60b60e2901967f98b9', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 20:20:37'),
('toufd846e4b4574aac6b8b33cd7524a097f', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'nologinuser', '2019-06-28 10:30:29'),
('toufd9c95231c8aa1ba6d231a7cb1dde695', 'itm_15b783a4ff1b3e29eb88e371e8f3f323', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-03 04:07:47'),
('toufddf36ab1d26abfbebe61d12d9a54852', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-02 06:32:22'),
('toufddfe95ed535634b2f6c6ff4a14cdf90', 'itm_2bb3fb13e1040cae112485657e31f868', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-19 13:34:04'),
('toufe2c1268d1a3a11c9c32c8b19fbe41fe', 'itm_86834f5b0a42fac1026ddc7e8c7b79e5', 'nologinuser', '2019-07-22 10:31:52'),
('toufe7116424504a80e3d19a65e4d61471e', 'itm_f1d3630ad14ea7139fa9c123f718cce1', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-23 22:29:10'),
('toufe86f8ac3cb61380008d8029adb2e9c4', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'nologinuser', '2019-09-26 04:13:20'),
('toufe8a3bfcf477e495e23ecd75e4eb0f37', 'itm_40c6367fee11bc95a223027f129e2bee', 'nologinuser', '2019-09-27 17:46:09'),
('toufe8d0dee1953c970af96b72e8f0132c5', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-12 18:45:58'),
('toufea0db128ab61adef065c295799f4761', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-04 22:54:12'),
('toufea27ec4971621ad8f7feeab32417f67', 'itm_0a3e52f579e7159ef651ef4ca0aef1db', 'nologinuser', '2019-09-10 22:23:27'),
('toufea5709f7d8060f3feb9d84f16bea95c', 'itm_1c85952ce6c4709784b40dcb24f8577f', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-24 06:08:28'),
('toufed43b5f9f12e9a8dcad692553acc984', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 04:54:19'),
('touff209fa6417bd5e787238e79c81afae0', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-28 00:35:06'),
('touff3026e45695b46e0da3ff4640457595', 'itm_a0ee873a872f79828676b8850c3e875a', 'c4ca4238a0b923820dcc509a6f75849b', '2019-08-28 15:32:09'),
('touff4af612367de6a04fb27a857178b061', 'itm_1b53dd85ee66f058795ee2971ad55fc8', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '2019-07-02 11:02:04'),
('touff55931742aabe2f5ec3447ba3832cc8', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'usre71177a5c87155245fc6d7ba39043963', '2019-08-30 06:46:55'),
('touff606f9f4aee06d3e57174411dd929d4', 'itm_feba75af4918ebee809bafb3c5e4c963', 'usr2143bfa9ba787c44d54468c26d7f485f', '2019-10-07 06:34:24'),
('touff65930b5163d81aa4407ca7ef956060', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-10-01 09:09:12'),
('touff73bd783b6c1af5540e8321850b930f', 'itm_54709837e70ae2f179b49a739ded54d7', 'nologinuser', '2019-08-30 03:03:17'),
('touff74439c6209f33502de3eabf3076735', 'itm_d688553dcae9083a4104754662ec4d26', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 20:27:33'),
('touff8a963330812853d86b682f89cbbd18', 'itm_941519b2438b0747386a6fee39e5b279', 'c4ca4238a0b923820dcc509a6f75849b', '2019-06-27 09:20:06'),
('touff996a84bb918f1089c56cbb55b25330', 'itm_52c74ad65651f004b6145559b25db4d5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-10-05 08:07:14'),
('touff9b20d60d9f48a11a818b8b0fde11f5', 'itm_cd86448707196d5f0ea6b555e0da6561', 'c4ca4238a0b923820dcc509a6f75849b', '2019-09-16 04:45:20'),
('touffa48f0d898cab9d80d1f34172573301', 'itm_8bcbbf7119510ee42cc2aba0dfc63fa2', 'usr1ffb51feddb5c60deeaacb5c644257c7', '2019-07-05 07:34:13'),
('touffab28cee42ba8016c7a16283837c1dc', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'nologinuser', '2019-09-19 10:47:25'),
('touffcfae0cd8fa3c6f82865de660d5f235', 'itm_f435477e7d52baac496a0be7621974c5', 'c4ca4238a0b923820dcc509a6f75849b', '2019-07-01 09:36:34'),
('touffdf95a30a9efc88f3312422ec50b338', 'itm_bfd764c6d3b307679d64c24525ca85c8', 'usr683ce32fff1cd60f2858c5f0ee3b52e8', '2019-09-02 20:35:20');

-- --------------------------------------------------------

--
-- Table structure for table `bs_version`
--

CREATE TABLE `bs_version` (
  `id` varchar(255) NOT NULL,
  `version_no` varchar(255) NOT NULL,
  `version_force_update` tinyint(1) NOT NULL,
  `version_title` varchar(255) NOT NULL,
  `version_message` text NOT NULL,
  `version_need_clear_data` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bs_version`
--

INSERT INTO `bs_version` (`id`, `version_no`, `version_force_update`, `version_title`, `version_message`, `version_need_clear_data`) VALUES
('1', '1.1', 0, 'New Version Available', 'New Version Available for update in Google Play.\r\n\r\n* Bug Fixed when press purchase at details \r\n\r\n* Bug Fixed for user point update\r\n\r\n* Device Compatibility', 0);

-- --------------------------------------------------------

--
-- Table structure for table `core_about`
--

CREATE TABLE `core_about` (
  `about_id` varchar(225) NOT NULL,
  `about_title` varchar(225) NOT NULL,
  `about_description` longtext NOT NULL,
  `about_email` varchar(255) NOT NULL,
  `about_phone` varchar(255) NOT NULL,
  `about_website` varchar(255) NOT NULL,
  `ads_on` tinyint(1) NOT NULL DEFAULT '0',
  `ads_client` text NOT NULL,
  `ads_slot` text NOT NULL,
  `analyt_on` tinyint(1) NOT NULL DEFAULT '0',
  `analyt_track_id` text NOT NULL,
  `facebook` text NOT NULL,
  `google_plus` text NOT NULL,
  `instagram` text NOT NULL,
  `youtube` text NOT NULL,
  `pinterest` text NOT NULL,
  `twitter` varchar(255) NOT NULL,
  `privacypolicy` text NOT NULL,
  `GDPR` text NOT NULL,
  `upload_point` int(11) NOT NULL,
  `safety_tips` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_about`
--

INSERT INTO `core_about` (`about_id`, `about_title`, `about_description`, `about_email`, `about_phone`, `about_website`, `ads_on`, `ads_client`, `ads_slot`, `analyt_on`, `analyt_track_id`, `facebook`, `google_plus`, `instagram`, `youtube`, `pinterest`, `twitter`, `privacypolicy`, `GDPR`, `upload_point`, `safety_tips`) VALUES
('abt1', 'Nice Product Powered By Panacea-Soft', 'Panacea-Soft is a software development team that focuses on helping your business with mobile and web technology.We tried our best to delivery quality product on time according clientâ€™s requirements and exceptions. We are technology oriented team so before we code, we analyse for your requirements and brain storm then start for development. We donâ€™t over promise to client and trying our best to deliver awesome product package. Thanks for reaching out to us. We are happy to listen your world and enjoy to solve the problem using technology.', 'teamps.is.cool@gmail.com', '+9592540942**', 'http://www.panacea-soft.com', 1, 'ca-pub-7127831079008160', '6882887537', 1, 'UA-79164209-2', 'http://www.facebook.com', 'http://www.google.com', 'http://www.instagram.com', 'http://www.youtube.com', 'http://www.pinterest.com', 'http://www.twitter.com', 'A privacy policy is a legal statement that discloses how a party gathers, stores, and uses the personal information it collects. Personal information refers to anything that can be used to identify an individual, including names, phone numbers, email addresses, device IDs, and locations.\r\n\r\n\r\nThese policies are used by companies and mobile app developers to stay compliant with federal laws. They fulfill the legal requirement to safeguard user privacy while also protecting the company itself from legal challenges.\r\n\r\n\r\nThe contents of these policies can vary greatly depending on the industry, user demographics, governing laws and jurisdictions, and application platform. Employing the use of third party services may also affect the need for one, as well as its contents–even if the app itself doesn’t collect user information.', '', 50, 'Meet the seller in person and transfer cash only if you have secured the item. As a seller, ensure that you have secured your payment and safely exchanged your item.\r\n\r\nDo not transfer money in advance!\r\n\r\nUnless verified by your own bank directly, don\'t trust any SMS or email about any money transferred to your account. Someone\'s claim to have paid extra money by mistake and asking to return/reverse is suspicious in nature and should be avoided in all instances.');

-- --------------------------------------------------------

--
-- Table structure for table `core_images`
--

CREATE TABLE `core_images` (
  `img_id` varchar(255) NOT NULL,
  `img_parent_id` varchar(255) NOT NULL,
  `img_type` varchar(100) NOT NULL,
  `img_path` varchar(255) NOT NULL,
  `img_width` varchar(20) NOT NULL,
  `img_height` varchar(20) NOT NULL,
  `img_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_images`
--

INSERT INTO `core_images` (`img_id`, `img_parent_id`, `img_type`, `img_path`, `img_width`, `img_height`, `img_desc`) VALUES
('img00ac6cb7203dcbcebe2078d4534430e9', 'feedce2c4e6f3b2d4a133bf1f9786b2e92ae', 'blog', 'food_2(1).png', '600', '400', 'desc'),
('img016375baed11a0fc80354c59cd90911e', 'manu2be8a1bda6ba563cbf1c289ddc6bd978', 'manufacturer-icon', 'chevrolet.png', '512', '512', ''),
('img01d727b25d51f284d40bffcf399f8fd0', 'subcatc4b6928dd0ba7f1f4ffc5a4588cea59e', 'subcat_icon', 'computers_icon3.png', '512', '512', ''),
('img02608f9a353a9daa4d0fa8ef4579dbb9', 'itm_709970eef73e104ca5d4114b2485355d', 'item', 'IMG_20190724_104702_182.jpg', '660', '660', ''),
('img02c4163095805cb70483044430879a6d', 'itm_92994f957013924861ccf81e6a8ea4dc', 'item', 'IMG_20190902_182557.jpg', '500', '500', ''),
('img03d6370302d7dd4aa5fefc0ab0713615', 'manue3ec39506fb31e8a9a32ded499455851', 'manufacturer', 'kia.png', '700', '430', ''),
('img04a6e9d9e64f031ac51020c3081bb293', 'cat445639833db3eff8b6cdb5510aa39faa', 'category', 'computers_cover.png', '600', '400', ''),
('img05797ff2cc56cc7182f70ea30b6a789a', 'notie3a73fc9d0d1cb34b1fc5fbb973d10cc', 'noti', 'david-pisnoy-669300-unsplash.jpg', '2448', '3264', ''),
('img05cb133cb4f8f88631aa0323fbf3522b', 'noti0b045f1dd581ef3237a9d09f3da9000e', 'noti', 'Webp_net-compress-image_(14)4.jpg', '1280', '851', ''),
('img062f9b5a866f72842d379c62725a16dd', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', '156818407574020190911003437.jpg', '187', '250', ''),
('img06ec7601535f6fee7e85d242ca51913a', 'feedf6bf282726b910430fa2c5931a556a38', 'blog', 'medicine_1.png', '600', '400', 'desc'),
('img07f6117f7ce2873bb6ffe8d60e7409c0', 'model9f102e76d7d3b03b576f8bd57d665026', 'model_icon', 'ta-icon.jpg', '920', '517', ''),
('img09791cb3d2ea11acc84772e1ee2ea696', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'item', 'IMG_20190724_141530_254.jpg', '1280', '853', ''),
('img0aab8242ec298b440a18b7e134b3a086', 'feedbf7024305368fea5897252665b9ca7c2', 'blog', 'a(23).jpg', '604', '340', 'Test'),
('img0ac4c932425c93ae32a86d28667272cf', 'usr84db7485fc9b8eeb0c74ed43fa8af040', 'chat', 'magazine-unlock-05-2.3.1390-_876D9B83618F47D0CFEDC5C03CA4FD4C20190703041743.jpg', '1440', '2560', ''),
('img0b48987d6068372e19fa6f25c540abee', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'item', '0.png', '800', '800', ''),
('img0b4e8820d85b4197bfcff027d4b74541', 'itm_1fdab9310eda2ad6b4f9d846991c7198', 'item', 'JPEG_20190830_104245_173410400.jpg', '3456', '4608', ''),
('img0b5ec5bf8fc258b2b2513dbc3b5823a2', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'item', 'IMG_20190724_104818_986.jpg', '600', '900', ''),
('img0ba82bfa8fad47fcde98b9665f3de33f', 'itm_ee73165b7c94f2eff3a4201b9fe73c6b', 'item', 'IMG_20190724_104630_534.jpg', '1280', '853', ''),
('img0c994a43ecf58ae9f8dc21c49c018b82', 'subcatcdf3f568c7dedef69f2aa372e544a3d7', 'subcat_icon', 'fashion_icon4.png', '512', '512', ''),
('img0cca4e47c990829d45bd0d3fb0396ad1', 'usrbad733f0fa26f94bc0c072a39d369a8f', 'chat', 'eyJpdSI6ImU4Y2U1YjBkMWVhOGMzNTNlZmFmNmNjYTQ3NWIyNTFmNTA0NThkNzA4MTk1Y2YyN2ZkNmNmNzUyZmE4NDQ0N2UiLCJ3IjozNTAsImgiOjIwMCwiZCI6Mi4wLCJjcyI6MCwiZiI6MH020191004122151.jpg', '700', '400', ''),
('img0f7169224a76864f6a81ced9e328df30', 'itm_3831c0d10b159d92e6e488dc2a13ed79', 'item', 'IMG_20190724_104816_382.jpg', '800', '800', ''),
('img0f789102e329fe0be20f53e4f8a3e110', 'feed6962717195222fd909e34ff2d08c5a26', 'blog', '6.png', '600', '400', 'desc'),
('img0fe12dab7d70bede7acef879dc5fa022', 'feedce2c4e6f3b2d4a133bf1f9786b2e92ae', 'blog', 'food_4.png', '600', '400', ''),
('img10a6ddc75ef07ce7f4e044cc55d2fd86', 'itm_d226e2115d5632e9aaaac89bd880737d', 'item', '10-28-17-noan71.png', '696', '522', ''),
('img111ee007b701a7040526855bfc4eaf41', 'feedccaca598699e47429206ffdcfb970e9e', 'blog', 'food_1(1).png', '600', '400', 'desc'),
('img116ca5e2243a7bd56357cc455eebd99d', 'usr3f57bf66eac7eebcf7cbecf84651d96c', 'chat', 'Webp.net-compress-image (14)20190704222017.jpg', '1280', '851', ''),
('img1173c0103e3b87b926322157bf0331bb', 'notie1bba29f85cf82cb4d0b664aca48be9b', 'noti', 'Webp_net-compress-image_(14)1.jpg', '1280', '851', ''),
('img11b97cd4393790578af5f02a6698c8bf', 'itm_aec300faa4929e3342679e55e63c24bc', 'item', 'FB_IMG_1563881218073.jpg', '720', '540', ''),
('img11f76d25c329116cdabef22b307c8e1e', 'usr3f57bf66eac7eebcf7cbecf84651d96c', 'chat', 'Webp.net-compress-image (14)20190704232658.jpg', '1280', '851', ''),
('img13b3a722dbccf785e74bd655603e9232', 'subcat0499ae94e6e2bf5e02ce4235051047d6', 'subcat_icon', 'phones_icon4.png', '512', '512', ''),
('img153b8e7b4bc464f846ebdc165fd7c04a', 'subcate3da9c8769ad3eaefcfa6ffb3ca6fb52', 'subcat_icon', 'phones_icon2.png', '512', '512', ''),
('img17581ae17bf116b4b6f44d2be98c1181', 'itm_ca2ed3c0fc02900d4a75791141ad0eb5', 'item', 'Wallpaper-v4-2560x1440.jpg', '2560', '1440', ''),
('img17f5412b1ac5e0ed4ea934fa0dd64553', 'feed399789809c27117114b75360eb3ca227', 'blog', 'electronic_2.png', '600', '400', ''),
('img1893b35d219f2f30fd3ce549d08a3e38', 'itm_668ad28236dab1d28bad214f5fc39fcd', 'item', 'bmwz44.jpg', '640', '426', ''),
('img1954466c209c974573490be68e4d5254', 'itm_3bc2dd9f6f7dbfcefb5238ae4a6ddf2c', 'item', '10-28-17-noan7.png', '696', '522', ''),
('img1a565cbadbc4f461501f15ca8b097c10', 'feed2e4b04cfc7586d0dc41b4abfb518b932', 'blog', 'card_2.png', '600', '400', ''),
('img1a57088e9f6f6fcab9ae5650070f5acd', 'subcatc58f3b37c72a22faa7a1e6fac40bf264', 'sub_category', 'ukele.jpg', '1200', '1200', ''),
('img1b6cc5c3655179921492166a4a458f1b', 'manuea8bed8f89456f6cae100cce1e88bdf1', 'manufacturer', 'honda.png', '672', '378', ''),
('img1be49d7624c1dcc4cb8f2f4584ab42d1', 'itm_528c99e1abd619f814f246f0daf3f734', 'item', 'ae_1567523082529.jpg', '1080', '1920', ''),
('img1c2a26304aa03d2830cde82298827c27', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'item', 'IMG_20190724_104611_345.jpg', '940', '694', ''),
('img1da75d6298241c4833fbd3af88b2bd56', 'itm_8c22c49be4fcb839586d508cb71bea62', 'item', 'magazine-unlock-01-2_3_1812-_6ef97afd2d3e47bba3546147f226a0bb.jpg', '1440', '2560', ''),
('img1e0827ee5d911f7e769676bd64f1b90e', 'subcatb1e75e347ed7f989cb1bcec390b1eac7', 'subcat_icon', 'sports_icon2.png', '512', '512', ''),
('img1e589b15b71028fda20b707c5cdabeed', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'magazine-unlock-01-2.3.1375-_E5A58282A4717F213DA893AC76D2EBB620190627031639.jpg', '1440', '2560', ''),
('img1e6db2897df922b2af6a4847da6915af', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'item', 'FB_IMG_15638810710991.jpg', '720', '540', ''),
('img1e7deb6e78440276bd7ee3db2151397d', 'cat3ed818f44f3a3e3d49a2798b4c51f5b8', 'category', 'properties_cover.png', '600', '400', ''),
('img1f4969aeb786bd0e65f4079a900f0c34', 'itm_e9a2e1f53e66c26581434625e495099a', 'item', 'bmwm33.jpeg', '640', '422', ''),
('img201ed3354bf5559c2602af72d3127392', 'manue3ec39506fb31e8a9a32ded499455851', 'manufacturer-icon', 'kia3.png', '512', '512', ''),
('img20682b7b72ea96b947c227c89eb68f9d', 'model8d62068148a2b2526fedbda4eee826e8', 'model', 'bmwm3.jpg', '800', '600', ''),
('img207e31e09e6046318985555fb7ae26a7', 'itm_0451dc50d46971ddfa285a7ba60a9c20', 'item', 'ae_15675230825291.jpg', '1080', '1920', ''),
('img20b13e8a3f707857259ab8aaefc71f3a', 'catfa070dd5cc2a2c9c6196159f85480ff7', 'category-icon', 'services_icon.png', '512', '512', ''),
('img22ae8a079c89c8d0ed1c3c652d35df14', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'item', 'IMG_20190724_104723_853.jpg', '833', '626', ''),
('img23c1ed8b9e990810936e2e3a0dddde0e', 'itm_52c74ad65651f004b6145559b25db4d5', 'item', 'JPEG_20191004_171040_5267190558043144582.jpg', '4000', '3000', ''),
('img23c85b57ea5c0254f5a47746b294703c', 'feed7fccca6a75b52b9f1713a8732bffd4e1', 'blog', 'clothes_2.png', '600', '400', ''),
('img24d40d516fa08ed531b2d9a4b26c3d5d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', 'chat', 'magazine-unlock-05-2.3.1390-_5AA635ADF90C0C2F971ACB5FAC1CF72520190704204036.jpg', '1440', '2560', ''),
('img25084027a46dc4d1e6b86ddb915ffe30', 'subcatc4b6928dd0ba7f1f4ffc5a4588cea59e', 'sub_category', 'notebook.jpg', '1500', '1000', ''),
('img274fcc3dc632037862851798f2f54efa', 'subcatc61744e20375d4e72666348bfacb3046', 'subcat_icon', 'phones_icon3.png', '512', '512', ''),
('img276c887e9c10f7da4eab4616a6b2adde', 'manu2be8a1bda6ba563cbf1c289ddc6bd978', 'manufacturer', 'gm.jpg', '620', '414', ''),
('img27a52f2077afc8f427f1803d11108630', 'itm_7359de3d150f84bd9e06d9e6054ba151', 'item', 'IMG_20191007_124254.jpg', '4000', '3000', ''),
('img27ae022f0a17b1a283e539f3cae23733', 'itm_69d4c9324c836f8fa7e05ef141f4a989', 'item', 'bmwz841.jpg', '2000', '1333', ''),
('img27c0841595f70cffa00559e645ffbf10', 'manudf9c1b40460fb53680fa5278f8ae8919', 'manufacturer-icon', 'toyota.png', '512', '512', ''),
('img28593618ebb02737fa8fa8d2ba7a9f13', 'itm_87b947184dd9b587dc94f9713a30a7c8', 'item', 'IMG_20190902_1826021.jpg', '500', '713', ''),
('img2944954df1db744bbcded01e4822ae45', 'feed143913da363b12f35bb44d583561d95e', 'blog', 'clothes_1.png', '600', '400', ''),
('img2b158c2dfd8008c2c1a731c192fe2b9e', 'model38555e6b1564cf8d73a53a3fca4c1f7a', 'model_icon', 'crz-icon.jpg', '1010', '566', ''),
('img2b8acd6b0e16d0b1e4ddc67962b3191d', 'subcat3f6a29e6c498b5e5bbcd0a5b3bf1dda4', 'sub_category', 'house.jpg', '1200', '800', ''),
('img2c65614cfee9361c6d6f974f41c552dc', 'feed2e4b04cfc7586d0dc41b4abfb518b932', 'blog', 'card_1.png', '600', '400', ''),
('img2e0434b8ab6bd71b28b40026ac3887e2', 'catc48a4778abd789c8a2cf00a7d4b1b154', 'category', 'event2.jpg', '1680', '1050', ''),
('img2eeee970c7d9304843a5f931cea4d76d', 'subcatd725dbc5bf8e386dc384594ed3290a7e', 'sub_category', 'guitar.jpg', '1000', '1000', ''),
('img30fe11e2842f265d6a667629f60d6cc3', 'feed3a6e6e7eef6d107a325440f3edbca146', 'blog', '8.png', '600', '400', 'desc'),
('img31c093d85d8b19c2a0eea35052f1ad00', 'itm_0434d45e5b5836778bcbe241c55b2b10', 'item', 'IMG_20190724_104718_507.jpg', '1000', '667', ''),
('img3284fe7ac4a474eb5d9b14fdca6f8556', 'subcat237b84c51a1134669e801fac81578b65', 'subcat_icon', 'properties_icon2.png', '512', '512', ''),
('img338e62fe304b79d7e72858cb4a24cf12', 'model891354c970ddbec3d44623f0a5cd5f89', 'model', 'omni.jpg', '390', '312', ''),
('img33987b68c200fdeaae80d2e225fa1853', 'itm_f3088ca542bd81c5a7d7bb11c78c69f2', 'item', 'SAVE_20190908_082758.jpg', '835', '219', ''),
('img34f38e781b554175859dc75f4a9d08ac', 'itm_79324075063082372ebcb5b759b2bf7a', 'item', 'magazine-unlock-05-2_3_2022-_41dd45178f1442468f74d584ce3dd4d6.jpg', '1440', '2560', ''),
('img3563dc2f10ed1fe14002ecdae19762f9', 'itm_3574960a0f206a81307cfea2804acbed', 'item', '1567138534481.jpg', '144', '176', ''),
('img364df8650461115719cac0af2aece8f1', 'subcat08f52e619858a83627bb8850d03cb4b3', 'sub_category', 'download_(24).jpg', '215', '234', ''),
('img3688412671a24621a79fc1bb7c9f0b05', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'item', 'IMG_20190724_104844_699.jpg', '1637', '1872', ''),
('img36989aa8183680a03b240ea3ca9b7cac', 'cata497304389495ab7c262383923243180', 'category-icon', 'phones_icon.png', '512', '512', ''),
('img36b691724e4fe5ae8c8fbd3594fb1ecf', 'feedfc6119d3a5cde7f85635ffabd0143d65', 'blog', '2.png', '600', '400', 'desc'),
('img376b70e5c8434459b3575c8acf059ee8', 'manudf9c1b40460fb53680fa5278f8ae8919', 'manufacturer', 'toyota-cover.jpg', '1600', '1200', ''),
('img3828b3f07f6ddb439e9703bf2a3e6b99', 'ban9befae4e71e14e40a01694caeed51c15', 'banner', '1.jpg', '3456', '5184', ''),
('img3a48f7cf46f2d1ca708e52f06dd8ad19', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'chat', 'Webp.net-compress-image (14)20190708000425.jpg', '1280', '851', ''),
('img3a5045929af43dfded9e0fee5882325c', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'magazine-unlock-01-2.3.1390-_310DBC05EDCAD82586E7B466A238E7CB20190703035737.jpg', '1440', '2560', ''),
('img3b6860e01c26b18a7ac303e04c5aa2c0', 'itm_f93a8f648594175a784c03603950f8cf', 'item', 'bmwm32.jpeg', '1200', '675', ''),
('img3bf07afa4046e89dd91792fee57c8318', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'item', 'IMG_20190724_104809_486.jpg', '3955', '2225', ''),
('img3cf90ad45916878d782931c8c41ca95a', 'itm_016531de690206f9e03cf036c334a467', 'item', 'bmw6006.jpg', '1100', '873', ''),
('img3cff5c173045139c9dbddd06a02080b9', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'Webp.net-compress-image (11)20190704204133.jpg', '1280', '853', ''),
('img3d388d86f6aa9031fc3c31bf9bde534e', 'itm_6e80825cb4fcaa19498d0acaac02efe2', 'item', 'ms_1_512_1557246.jpg', '512', '633', ''),
('img3da855c4563f5fae6d0b99883726be5f', 'catd2c5389d742dec9587a88b5ea6edcf1d', 'category-icon', 'astronaut_(1).png', '512', '512', ''),
('img404d65e97c82f14639374a62b24fb857', 'itm_fd55561554addf7abc99ee1d574e4af7', 'item', 'bmwz43.jpg', '1024', '683', ''),
('img4069073bd9542f57e43b03859b933275', 'manu03e8303f6bbbfba22ca2c4e867d4f182', 'manufacturer', 'suzuki.png', '1123', '399', ''),
('img408b57ff92dfb8740f6acb9731ae6749', 'noti226157e9070efa6d8d9e2489dc19a814', 'noti', 'Webp_net-compress-image_(14)5.jpg', '1280', '851', ''),
('img40d33cb58d2be8c94a979775ebca3706', 'usr84264292f0b3360143f6ea8776b77f20', 'chat', 'Screenshot_20190914-122632_FarmConnekt20190914213721.jpg', '1440', '2960', ''),
('img40f271b005cb2d77e6bf7028ed723446', 'itm_3a197e7a07668e4d8580e5b129957107', 'item', 'IMG_20190814_0920397.jpg', '3456', '4608', ''),
('img416d3984418516f4413ce9681eebd825', 'itm_feba75af4918ebee809bafb3c5e4c963', 'item', 'Screenshot_20190330-000438.png', '720', '1280', ''),
('img416e9cc52e078dcccb876901672f8815', 'itm_40c6367fee11bc95a223027f129e2bee', 'item', '2018_02_25_17_22_35.png', '480', '854', ''),
('img4428f0ac78f82c00e090b7c6dc8f6493', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'chat', 'magazine-unlock-01-2.3.1397-_1030ed04f78540f28179f67cde66233120190708000159.jpg', '1440', '2560', ''),
('img47d9be7b4fe523d6b7f2d575a07c94ce', 'feedc06aa8c73ef6b5986e1d57e68377b5ae', 'blog', 'a(8).jpg', '509', '340', ''),
('img4889c028df6697a713eddd4a62c3f6c7', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'item', 'IMG_20190724_104615_604.jpg', '1280', '1024', ''),
('img49d93966e45c17fe5141b24a3ca80d3b', 'noti217badcd797b369a6e8d7e1bf5d0337e', 'noti', 'itsgreatoutthere-unsplash-01.jpg', '1200', '800', ''),
('img49dab298476177901001ee2b94abe7f4', 'feed399789809c27117114b75360eb3ca227', 'blog', 'electronic_2.png', '600', '400', ''),
('img49de57273ba064a81e5b065cef4b869d', 'model602f53b845adfab3b1712ce60a23f389', 'model_icon', 'bmw3.png', '512', '512', ''),
('img4a011bf623ce5623d8b098b468314491', 'itm_f5018eae265178088239ff0e5541a663', 'item', 'IMG_20191019_151139.jpg', '4160', '3120', ''),
('img4a15bdaca1be438c02a80c576cb74b18', 'itm_a88e7b941c622da8ea94af30f6147997', 'item', 'ms_1_512_2826132.jpg', '512', '474', ''),
('img4c0cc76dfea654fa3539013cbdfff886', 'itm_33a66429f209514b7fd2c128fbf1f88d', 'item', 'IMG_20190724_104657_691.jpg', '955', '1200', ''),
('img4c2d3c85196e14e9f948b97b176687b1', 'model38555e6b1564cf8d73a53a3fca4c1f7a', 'model', 'crz.jpg', '400', '400', ''),
('img4c6646f4a3845a065229059517b487bd', 'notia4f5cc0da551a0d999c0ee13504b072b', 'noti', 'electronic_31.png', '600', '400', ''),
('img4cda349095ad8625bc2530bbb7a9fcf2', 'feedbf7024305368fea5897252665b9ca7c2', 'blog', 'food_3(1).png', '600', '400', 'desc'),
('img4e0d76a4c0870a2d265cb8594f6df781', 'subcatd725dbc5bf8e386dc384594ed3290a7e', 'subcat_icon', 'musics_icon3.png', '512', '512', ''),
('img4e84047ef258c8f059f5c17a2be365ad', 'feed72dc3543d6c48fa4abe261dd83de53d9', 'blog', '5.png', '600', '400', 'desc'),
('img4ea6379a55a9faf3a1688af650308087', 'itm_6cb233f6c94ea092d2202e0c5d6d6229', 'item', 'bmwz831.jpg', '880', '600', ''),
('img4eddfff32282f0c03f3cc57043fbf27a', 'itm_348514eae52cb1c602c82153ffa456fc', 'item', 'bmwx31.jpg', '800', '450', ''),
('img4ef295809ac5ed9c1cce71656bafc655', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'chat', 'magazine-unlock-05-2.3.1397-_b0541fa3d1754026af6e9b3cd109e73f20190708000145.jpg', '1440', '2560', ''),
('img4f0c5a8444ac20a7f16b624c076c8313', 'subcat30618a4244d996f117ef347fc7718886', 'subcat_icon', 'musics_icon1.png', '512', '512', ''),
('img4f2d23c9915d38f6fdcb8cfafe9f69e3', 'itm_19d8a632ca279d2a4739913172cb71dd', 'item', 'FB_IMG_1563880976112.jpg', '720', '842', ''),
('img4f86ad17e00a233836172e9da1e06a5c', 'model647a0bacbe01ba015cc14252721483b7', 'model_icon', 'gaming_collection_2.jpg', '600', '400', ''),
('img4f9870103de0107cf7f152ce19707951', 'itm_33421d314109f37e8cff807cd4149cee', 'item', 'IMG_20190814_0920394.jpg', '3456', '4608', ''),
('img4fc44f7a1a897b58e3bc806bd13a7d86', 'subcat0499ae94e6e2bf5e02ce4235051047d6', 'sub_category', 'download_(23).jpg', '206', '244', ''),
('img506997e230f327d4e66411438b89e6fb', 'noticb8657dc0b0158ca9168ae6bc98254c0', 'noti', 'cynthia-del-rio-88318-unsplash.jpg', '5184', '3456', ''),
('img50eba79dd5db41ddb990bcef53999dc3', 'catc48a4778abd789c8a2cf00a7d4b1b154', 'category-icon', 'eventicon3.png', '225', '225', ''),
('img51a376848329be59608d57292de95f0b', 'manu77e62e7ae4effdc79e5b211c60665c3d', 'manufacturer-icon', 'ford1.png', '512', '512', ''),
('img53335cbc23885e1b9080454a360a4edc', 'usr84264292f0b3360143f6ea8776b77f20', 'chat', 'IMG-20190909-WA000020190909035728.jpg', '470', '431', ''),
('img541db0fe8b7e6ef428e9dd2ffb9fcc75', 'itm_54709837e70ae2f179b49a739ded54d7', 'item', 'IMG_20190724_104758_965.jpg', '225', '225', ''),
('img558c1c5eb26c4eb20ec73a0bc009b32c', 'notif34dffb87f4adaf7a0a38209f8f56588', 'noti', 'Webp_net-compress-image_(14)3.jpg', '1280', '851', ''),
('img56e39f1a57883c139fc00d8115d46a96', 'itm_81e1b4c39551a5765743662e1f5f079b', 'item', 'IMG_20190814_0920395.jpg', '3456', '4608', ''),
('img576c623e33ebe60140df1c2b2c8a3c08', 'subcatc58f3b37c72a22faa7a1e6fac40bf264', 'subcat_icon', 'musics_icon4.png', '512', '512', ''),
('img57b03caa3473f23ead8f3a502a6f0b4c', 'subcat237b84c51a1134669e801fac81578b65', 'sub_category', 'apartment2.jpg', '1280', '853', ''),
('img582a9bf381c0f13b86b933a15069d3ab', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'eyJpdSI6ImU4Y2U1YjBkMWVhOGMzNTNlZmFmNmNjYTQ3NWIyNTFmNTA0NThkNzA4MTk1Y2YyN2ZkNmNmNzUyZmE4NDQ0N2UiLCJ3IjozNTAsImgiOjIwMCwiZCI6Mi4wLCJjcyI6MCwiZiI6MH020191004122220.jpg', '700', '400', ''),
('img584c2b3c34a5a0ce8f7ca54719e30f88', 'subcat88be821e3cb84cc0c049f7d92028fe48', 'sub_category', '2k.jpg', '1280', '759', ''),
('img59bf21da25e0bd4f24947ffbf31dc51d', 'subcatc2c047936bef5ff528f790680e3ed083', 'sub_category', 'images_(22).jpg', '250', '202', ''),
('img59fca0a58e0a8f1d51f2cf3bd86cbb6a', 'itm_2446dc20339a5d9658a60c9315ef26e8', 'item', 'IMG_20190724_104812_706.jpg', '1200', '900', ''),
('img5a395f140870156075331b6733061fac', 'subcat0cc28d586f90805b3a569178b16b1fc6', 'subcat_icon', 'computers_icon1.png', '512', '512', ''),
('img5a460c2f1d6227d31315573176ef2b6c', 'cat445639833db3eff8b6cdb5510aa39faa', 'category-icon', 'computers_icon.png', '512', '512', ''),
('img5a6fad55e94e0b0a1c5bf0a0268a7b49', 'cat3988c9afad482db9a872ee5c19c34fbb', 'category-icon', 'musics_icon.png', '512', '512', ''),
('img5bee2ee99ef4c5bd8e356c71134b1c27', 'feed8d521e86017cf8aa7f4883a2fb67f068', 'blog', 'a(11).jpg', '478', '340', 'old blog'),
('img5c15a81207e63eddc1e5b8bfe294620a', 'feedf6bf282726b910430fa2c5931a556a38', 'blog', 'medicine_1.png', '600', '400', ''),
('img5c1adc4214025211acb9d6315c9ebf04', 'usr84db7485fc9b8eeb0c74ed43fa8af040', 'chat', 'magazine-unlock-05-2.3.1375-_80002ea70fa94c37bd434f7776b6b71e20190703042900.jpg', '1440', '2560', ''),
('img5d25b72b7ece82563a8f2f6e773b873f', 'subcat3f6a29e6c498b5e5bbcd0a5b3bf1dda4', 'subcat_icon', 'properties_icon1.png', '512', '512', ''),
('img5f0026b7cb3409bff8687b7b480b2fa7', 'subcat30618a4244d996f117ef347fc7718886', 'sub_category', 'download_(10).jpg', '275', '183', ''),
('img66679ce162f2f9c2c6880a0372579932', 'itm_cced99dff3ef98ebe1acba11f6606378', 'item', 'bmwx35.jpg', '640', '477', ''),
('img67d5733100cc5dfeeacbd2dd79173ab9', 'itm_ba0837310e18f29408f09fca748271ef', 'item', 'IMG_20190724_104834_070.jpg', '640', '519', ''),
('img67fd5b00607afba2c9100a6d33a9d7bc', 'modelcca748a5ea71fbd5d017f6d2bf2fa36d', 'model', 'bmwz4.jpg', '600', '338', ''),
('img6845e21ce3b527a7efefc406714c21cb', 'itm_bf0ddb1eef5ed6561f3185a05f849d89', 'item', 'bmwz411.jpg', '1920', '1080', ''),
('img696dbfa1eca354caeb461bf925214720', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'item', 'IMG_20190724_104801_838.jpg', '560', '560', ''),
('img6b7d862132c70929da29adb422616ea8', 'itm_0500dcce80c7d70b79453a8ff4096428', 'item', 'IMG_20191006_162316.jpg', '3120', '4160', ''),
('img6b8a6194175fbc22628261c6dcebde86', 'itm_ae3d4113f3dc1fb656c1ad5ebebe633b', 'item', 'IMG_20190724_104607_812.jpg', '940', '622', ''),
('img6ca544f7b1cac3af57f4709b435c9328', 'feed54717707cd5d9eaa115be9e8704066f3', 'blog', 'x_mas_2.png', '600', '400', ''),
('img6cec42a8e46e3eabb7ad006fd61e3026', 'noti97225230a7badf761c5d2e4542b616cb', 'noti', 'card_3.png', '600', '400', ''),
('img6d12adfbfabab1e282e1dd99993eae4f', 'bana9ec1f43cadeeec6841c523290cd185e', 'banner', '4.jpg', '6000', '4000', ''),
('img6d8276aec10bfadbb8f78968ff2ed407', 'itm_b6ca601edf04436a7e2516a36b5f0e8f', 'item', 'bmwx33.jpg', '795', '530', ''),
('img6de140a7895956a8481432ad63045ed6', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'manufacturer-icon', 'bmw.png', '512', '512', ''),
('img6f25945eac18c9ab1f62860aea6109b4', 'subcat68bf2c365c88382966dd72a347fc3473', 'sub_category', 'computertech.jpg', '304', '166', ''),
('img6f5cc37188e1d21266d639ddc8501bbf', 'subcat6226dfba4c0d4d8f9130cc2d5c8a5a58', 'subcat_icon', 'fashion_icon3.png', '512', '512', ''),
('img708fe46b885af4e831dcd5bd7e597e95', 'feed4e6ed4ee41d2823b51b7e3afdf8306ac', 'blog', '4.png', '600', '400', 'desc'),
('img71d94420abd0004af0fe7e107ef6d2d9', 'subcat7768ede02fa1e337b6270209cf83c8ce', 'subcat_icon', 'fashion_icon5.png', '512', '512', ''),
('img73101e6d412487a700cf19fe4264080b', 'itm_92994f957013924861ccf81e6a8ea4dc', 'item', 'IMG_20190902_182602.jpg', '500', '713', ''),
('img73a7ef14195a9e7a136252562704540c', 'subcat0132c5a6635b0a9d5c55278ebf226ad7', 'sub_category', 'doctor.jpg', '800', '600', ''),
('img743b421bbde30a9cb01404d6813fe2e5', 'subcat7768ede02fa1e337b6270209cf83c8ce', 'sub_category', 'skirt2.jpg', '196', '257', ''),
('img7487994c03c52fc32c6a7e891e1db195', 'feedf6bf282726b910430fa2c5931a556a38', 'blog', 'medicine_2.png', '600', '400', ''),
('img757ec19132382ac4dac4ed520c0c98d4', 'cate2f7ee7f7317ceddf4395f82665b5a86', 'category-icon', 'fashion_icon.png', '512', '512', ''),
('img75ca7bf538b22de4165ff1f64d9d47df', 'notic9a3ad3dca6672cc5075efbc9ca39ccc', 'noti', 'burger1.jpg', '1280', '720', ''),
('img76640cb68f1ece56c4601a7a41527375', 'subcat9b35a9ef1469be03db203811634f5ffc', 'sub_category', 'westerndress.jpg', '1000', '1000', ''),
('img77652e842b2b2e9935025f1b9e8af7cc', 'cat5dfc0138547b926dccc5ee269b1cd042', 'category', 'sports_cover.png', '600', '400', ''),
('img77bb72d8ac54e5cd3b117515f2690087', 'subcatc2c047936bef5ff528f790680e3ed083', 'subcat_icon', 'phones_icon1.png', '512', '512', ''),
('img780a9ee922720d7589aedb6c16662e4a', 'subcataa581788260871cce0607c7137b06036', 'sub_category', 'ultrabook.jpg', '1200', '675', ''),
('img79180b67226c25a939020388638b24d7', 'itm_8c22c49be4fcb839586d508cb71bea62', 'item', 'magazine-unlock-01-2_3_1952-_70e14f84da274836b5ca3354faf78710.jpg', '1440', '2560', ''),
('img7c3083ab5433cfcb2241f1e7af637b10', 'itm_2bb3fb13e1040cae112485657e31f868', 'item', 'FB_IMG_1563882504633.jpg', '720', '731', ''),
('img7c6180c88a1a8d7b3c4ba0763760385a', 'model602f53b845adfab3b1712ce60a23f389', 'model', 'bmwx3.jpg', '1166', '550', ''),
('img7cc208ba933dfe184d4938fc8c1389eb', 'feed7fccca6a75b52b9f1713a8732bffd4e1', 'blog', 'clothes_3.png', '600', '400', ''),
('img7d960a09704d25334f010a4eff6253ea', 'usr3f57bf66eac7eebcf7cbecf84651d96c', 'chat', 'Webp.net-compress-image (14)20190704232649.jpg', '1280', '851', ''),
('img7e3efe0e15d9e1b458b74ad4c59c0e86', 'ban37f83cc220683f5fe95bfb6da03131c3', 'banner', '5.jpg', '4016', '6016', ''),
('img823f30f0add5fa59d67b68c41a95ab83', 'model26cd1d7b29739880ae5cacb9989fb62d', 'model_icon', 'bmw5.png', '512', '512', ''),
('img824becd832966cb9378664d66b489513', 'itm_33a66429f209514b7fd2c128fbf1f88d', 'item', 'IMG_20190724_104654_861.jpg', '1024', '753', ''),
('img82c95b157de77533d77490cdfdb7eaf7', 'model3dfac2764f4caf50477e9693141fbcc3', 'model', 'rav4.jpg', '800', '400', ''),
('img83e678d68643c303c27bee6c563ca5ff', 'itm_c80942f3ecbc906985d16f32f4355072', 'item', 'IMG_20190724_104625_876.jpg', '960', '1280', ''),
('img8509fa4596d3abbe4f23f1a2aa053a8f', 'usre71177a5c87155245fc6d7ba39043963', 'chat', 'IMG_20190901_10415820190902133119.jpg', '1944', '2592', ''),
('img85395790b77903fb9e55513fde7d32d5', 'model52842c22cf5d90d1dcaf40f10207a674', 'model', 'bmw600.jpg', '640', '425', ''),
('img85552dee55216c756f1c5692a85aca38', 'itm_533a2c3363d5bf2f7eafc9b2374c1940', 'item', 'bmwz811.jpg', '900', '579', ''),
('img85ec78ddea3be3de213943fe2d47ec53', 'usr84264292f0b3360143f6ea8776b77f20', 'chat', 'orsrc3892420190928122029.jpg', '1440', '2560', ''),
('img86e6742c292e78fef8de85664575809c', 'feed399789809c27117114b75360eb3ca227', 'blog', 'electronic_3.png', '600', '400', ''),
('img871df2edbac345b83e7e0c18373020ff', 'subcatc61744e20375d4e72666348bfacb3046', 'sub_category', 'download_(32).jpg', '160', '212', ''),
('img88f93a4c7826843be57b040ffff91629', 'modelcca748a5ea71fbd5d017f6d2bf2fa36d', 'model_icon', 'bmw4.png', '512', '512', ''),
('img8ac658c8b1706e06312d867c10d4c4f8', 'modelb69e8ea91dfe143b8c7cad5d9a0b730f', 'model_icon', 'berano-icon.jpg', '1300', '775', ''),
('img8bb0666a0ef92c8c12f7e2dbcdafa2ba', 'usr84264292f0b3360143f6ea8776b77f20', 'chat', 'IMG_20190829_14165620190829034215.jpg', '3120', '4160', ''),
('img8bb075308144a38add63f0dc4bb05b7a', 'subcat96f05c8a49c61ee601800578d6d73175', 'sub_category', 'driver.jpg', '750', '500', ''),
('img8c4a0b46c3d073baec74015635c7814b', 'subcatb1e75e347ed7f989cb1bcec390b1eac7', 'sub_category', 'sportshirt2.jpg', '940', '591', ''),
('img8c7353af380c081178656040dfbfdbaa', 'subcat08f52e619858a83627bb8850d03cb4b3', 'subcat_icon', 'phones_icon5.png', '512', '512', ''),
('img8cd7597e5f2938d4dfc76046bb929aeb', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'chat', '00e523cf665d314742a1abcd07f7c1c620190904162638.png', '1500', '802', ''),
('img8d0a8ecf0f3e9751630c2cd424a4c069', 'manu77e62e7ae4effdc79e5b211c60665c3d', 'manufacturer', 'ford1.jpg', '900', '596', ''),
('img8d62bbd69862ff5f16ba41204071dbab', 'subcat3c0d35feb4c9e82599bf6f8bfbd08405', 'sub_category', 'myanmardress.jpg', '794', '584', ''),
('img8e13640fd9927ecba83139d126319efd', 'itm_7d7b97c7cc548e1d70e57f7a93c79e73', 'item', '10-28-56-54a08f440ac87229c787c4a7d4d7c2e1.jpg', '1920', '1080', ''),
('img8e762256593d3e4b7cb770faaff1706c', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'magazine-unlock-01-2.3.1390-_53A80E8DFDB9DCB137C18BCD58B26A1C20190703211229.jpg', '1440', '2560', ''),
('img90e29d3354998ec9554f9ed01cf81ae8', 'feed54717707cd5d9eaa115be9e8704066f3', 'blog', 'summer_3.png', '600', '400', 'desc'),
('img91cd66d99a9fe9eea04519ab2eb331ab', 'band1330f9da20ad21a806154a93f0d5fca', 'banner', '3.jpg', '3456', '5184', ''),
('img9285ee1d0c29512341c5324e2776668f', 'itm_1a7f3815a51c3c3888ede85bbacb9b88', '', 'hotel1.jpg', '1280', '960', ''),
('img930c844689f0dd00764be05e5ac23e10', 'itm_54709837e70ae2f179b49a739ded54d7', 'item', 'IMG_20190724_135937_642.jpg', '919', '900', ''),
('img944788ba31fd93cf8ea97c9c2d914662', 'itm_429cfc63572942f8e0bf470c4830dbcd', 'item', 'IMG_20190724_104618_941.jpg', '1280', '1024', ''),
('img9467a876d6827345854675c054b1613c', 'usr3f57bf66eac7eebcf7cbecf84651d96c', 'chat', 'Webp.net-compress-image (14)20190704232711.jpg', '1280', '851', ''),
('img969a43a7da62e6c2bd3a99ba0a1a0536', 'itm_cc8f2cbbfe679b77de58a638338b447c', 'item', 'bmwx34.jpg', '600', '400', ''),
('img97f8c857320147a3e494a80f62cd83c3', 'itm_3fb1417b6a7b46345d6cdb588e992235', 'item', 'bmw6001.jpg', '1600', '1200', ''),
('img98ddd5243b82f4243e8cef8c9f8404e3', 'itm_cd86448707196d5f0ea6b555e0da6561', 'item', 'IMG_20190724_104855_617.jpg', '700', '425', ''),
('img99203520ac888e3b992fb06ff5f6776f', 'model891354c970ddbec3d44623f0a5cd5f89', 'model_icon', 'omni-icon.jpg', '1280', '780', ''),
('img992a25f269b920ecc3f5a8e027d238d9', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'chat', 'magazine-unlock-02-2.3.1328-_1C14A117038531370CAA24BCB314AA3020190705004208.jpg', '1440', '2560', ''),
('img9b0964788db5236f8eb70e03983159f2', 'itm_ba0837310e18f29408f09fca748271ef', 'item', 'IMG_20190724_104750_545.jpg', '569', '569', ''),
('img9b1f64132b990c6ae227f0db6cf41f1d', 'itm_19d8a632ca279d2a4739913172cb71dd', 'item', 'FB_IMG_1563880979579.jpg', '720', '540', ''),
('img9b9752ec33fc24d19bb0f8176e65e0b8', 'subcataa581788260871cce0607c7137b06036', 'subcat_icon', 'computers_icon2.png', '512', '512', ''),
('img9cb24379ed8e84646d90b9f87aba813a', 'cat5dfc0138547b926dccc5ee269b1cd042', 'category-icon', 'sports_icon.png', '512', '512', ''),
('img9cedf2c25c3612ffc0c89c9c6a3395a5', 'usre59502e68b1d7c9b313e5962aaed6260', 'chat', 'IMG_20190807_13361520190909123749.jpg', '4160', '3120', ''),
('img9d9d6ae16b78bb96c66f4fdf88c93739', 'itm_422f1f7006c5a0e0c5c67e337de2c0ac', 'item', 'Screenshot_2019-11-26-15-47-18-143_com_panaceasoft_admotors.jpg', '720', '1440', ''),
('imga0b1a63a469fe39b04bc6d6b503df1ca', 'subcat00ec4d9a87ab18c9793c18fd587f6cb2', 'subcat_icon', 'musics_icon2.png', '512', '512', ''),
('imga120bbfdc07dd08733e7226d473e79eb', 'subcat96f05c8a49c61ee601800578d6d73175', 'subcat_icon', 'services_icon3.png', '512', '512', ''),
('imga170209db7a66b071327e0b27b3cd6f6', 'itm_c80942f3ecbc906985d16f32f4355072', 'item', 'IMG_20190724_104622_541.jpg', '853', '1280', ''),
('imga17f869925d7d4a1466ab8d3afb29b1f', 'itm_a0ee873a872f79828676b8850c3e875a', 'item', 'IMG_20190724_104823_899.jpg', '225', '225', ''),
('imga1ac0010ad566db31ccd3cf424b3995b', 'noti5133d4cca0f78d28528fe89da2041168', 'noti', 'card_2.png', '600', '400', ''),
('imga362ff8dfbc2084a5b35927db2a3fbf4', 'itm_d7014cc53954ab76dacbe38d12a9dedb', 'item', '1567397585908.jpg', '144', '176', ''),
('imga69de7c4eb4c551b7629184bbe4b24f7', 'itm_e82bfe1a2f355582dad722ebc2f3851f', 'item', 'ms_1_512_28261321.jpg', '512', '474', ''),
('imgabad28219c7e52befe29f26134ab504f', 'manuea8bed8f89456f6cae100cce1e88bdf1', 'manufacturer-icon', 'honda2.png', '512', '512', ''),
('imgaca2c4b3e9bb8d3e9d3d7fcf8bc7f42d', 'feed399789809c27117114b75360eb3ca227', 'blog', 'electronic_1.png', '600', '400', ''),
('imgadccdc2566c35399f1cbebc58698f745', 'itm_8d2c7208b461f1abbbe467e5059dd60a', 'item', 'IMG_20190724_104847_701.jpg', '466', '533', ''),
('imgae9b32eb03435aecaa58117ac59ca20b', 'catd2c5389d742dec9587a88b5ea6edcf1d', 'category', 'Internship2.jpg', '323', '156', ''),
('imgaeeb7e005d078f10ab5d0d612da99357', 'itm_602c61360a8f91f17bf9619c0e5a45a2', 'item', 'bmwz851.jpg', '1600', '1067', ''),
('imgb13d22a069520524117f209af2f13991', 'itm_8277fd5c824ab26ec25f902c7026a40c', 'item', 'Screenshot_20190330-0004381.png', '720', '1280', ''),
('imgb31f17e860e4a43192d16d2c7bd212f1', 'usr4edb805ca791dc7677ba4a785873debd', 'chat', 'magazine-unlock-01-2.3.1375-_E5A58282A4717F213DA893AC76D2EBB620190703202133.jpg', '1440', '2560', ''),
('imgb37d129a3587afe918417b3c7f715038', 'feedccaca598699e47429206ffdcfb970e9e', 'blog', 'food_5.png', '600', '400', ''),
('imgb45e7c18511abad20570d8a98c04cc98', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'chat', 'magazine-unlock-01-2.3.1375-_9518100b3efb457a82715d74d8370dcf20190703031719.jpg', '1440', '2560', ''),
('imgb83aff6cc2b58e81312c6d8216f5bc93', 'notia12d6755b0fe70765f8834a10cd51e6e', 'noti', 'a_(14)4.jpg', '523', '340', ''),
('imgb8b9a4c9c7738f7d33644ef1502f7e1f', 'itm_bfd764c6d3b307679d64c24525ca85c8', 'item', 'JPEG_20190902_152903_1301467698.jpg', '3456', '4608', ''),
('imgb9b9dbc583a12092607f15227f41198b', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'chat', 'magazine-unlock-05-2.3.1390-_5AA635ADF90C0C2F971ACB5FAC1CF72520190705005601.jpg', '1440', '2560', ''),
('imgbb5d94b8d92e149c43576383a702b2ed', 'model3dfac2764f4caf50477e9693141fbcc3', 'model_icon', 'rav4-icon.jpg', '640', '480', ''),
('imgbc0ab17a6a63d6642b0e7df874f0d795', 'itm_d688553dcae9083a4104754662ec4d26', 'item', 'IMG_20190814_0920396.jpg', '3456', '4608', ''),
('imgbd5f0da031b92be3c7635b57b13e6faf', 'feed54717707cd5d9eaa115be9e8704066f3', 'blog', 'x_mas_3.png', '600', '400', ''),
('imgbe15f07984161a02a14574c34c39c001', 'itm_ea7c87b1758bad40efccb3caedb6a1d1', 'item', 'IMG_20190724_104805_649.jpg', '1280', '720', ''),
('imgbed1df7ebf91c422ce07e0c3233df8ec', 'itm_3d2a1d910b1a5e1586c0e22cd568ce0a', 'item', 'bmw6002.jpg', '640', '480', ''),
('imgc127a6d255eddb97504afa0a3a07a0dd', 'usr3f57bf66eac7eebcf7cbecf84651d96c', 'chat', 'Webp.net-compress-image (14)20190704232542.jpg', '1280', '851', ''),
('imgc3512a6d78ac37822251152b755612ce', 'subcat00ec4d9a87ab18c9793c18fd587f6cb2', 'sub_category', 'images_(5).jpg', '300', '168', ''),
('imgc438ddd08224ffa8773da682cf641b1a', 'subcat6226dfba4c0d4d8f9130cc2d5c8a5a58', 'sub_category', 'forkids.jpg', '193', '261', ''),
('imgc63cc5cecb2cf1618db492133b992717', 'manu03e8303f6bbbfba22ca2c4e867d4f182', 'manufacturer-icon', 'nissan.png', '512', '512', ''),
('imgc82b88a571be4601a9e83883859d6353', 'manuaaa4b4db89d7150643621dcaf043ddd7', 'manufacturer', 'psa.jpeg', '640', '362', ''),
('imgc8b9055a793b95b923fb06ca2c8a8e24', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'clothes_220190618060908.png', '600', '400', ''),
('imgc94b7c3608ce365993478186ec570ea1', 'modelb69e8ea91dfe143b8c7cad5d9a0b730f', 'model', 'b1.jpg', '720', '340', ''),
('imgc98132414e8a691f404900d51f3d0068', 'feed64a7346dc26c2e8642fbeab5d4d345c9', 'blog', 'a(4).jpg', '509', '340', 'ddd'),
('imgc9e4de0bac884492310dfb0560b2f250', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'magazine-unlock-05-2.3.1375-_3a8fae5a79ea4119aafc431d4eb5bccc20190627030948.jpg', '1440', '2560', ''),
('imgc9fd3614872eaf6d966b9c3d8c331c95', 'feed2b30c87a3712da060e7afe346c3942fa', 'blog', '7.png', '600', '400', 'desc'),
('imgcac0a2c031ec2ead7419558ec4124cce', 'itm_070b8760ea6d199fa935644177cdd88c', 'item', 'bmwm35.jpg', '1536', '1024', ''),
('imgcb0940db79b77e689f5ececa7835a904', 'feedce2c4e6f3b2d4a133bf1f9786b2e92ae', 'blog', 'summer_1.png', '600', '400', ''),
('imgcda14d8b4596316def77dc53689440f0', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'magazine-unlock-05-2.3.1390-_8880E9535347ABB6C85AECB1651B3DF520190704203637.jpg', '1440', '2560', ''),
('imgce479bd94895e5255fc846dace9a4331', 'manu27aad03ae2ca70c3e527e6838c6ae86c', 'manufacturer-icon', 'tesla1.png', '512', '512', ''),
('imgcf2fbaab0c6fa16c8da5f4e4972bd97c', 'itm_f1d68517076ac7a74dee2dacbe655b90', 'item', 'IMG_20190724_141527_198.jpg', '1280', '840', ''),
('imgcf39e7676c63d83c81048daedec66f28', 'subcat88be821e3cb84cc0c049f7d92028fe48', 'subcat_icon', 'sports_icon1.png', '512', '512', ''),
('imgd1c50f9149ba498d00d24cf9075650f3', 'subcate3da9c8769ad3eaefcfa6ffb3ca6fb52', 'sub_category', 'download_(26).jpg', '225', '225', ''),
('imgd48281afd30440983129ad21cb277478', 'usr84db7485fc9b8eeb0c74ed43fa8af040', 'chat', 'magazine-unlock-05-2.3.1375-_FD450E14FA7B9D1C28BA732522AF199220190703042629.jpg', '1440', '2560', ''),
('imgd661ee5e82d872c3b57a0c27b7b374cd', 'itm_2bb3fb13e1040cae112485657e31f868', 'item', 'FB_IMG_1563882500699.jpg', '720', '720', ''),
('imgd6ca56daf9c3752aa8d378de4d68acde', 'itm_9ee01397bc7b3a5eb3696917fb9edbe2', 'item', 'bmwz821.jpg', '800', '600', ''),
('imgd71a61cf51bbee40b759a9e9cb86d80d', 'usr84db7485fc9b8eeb0c74ed43fa8af040', 'chat', '156153385149420190626023046.jpg', '3120', '4160', ''),
('imgd90339ff1c2d6df39d39e9596219e0b1', 'subcat9b35a9ef1469be03db203811634f5ffc', 'subcat_icon', 'fashion_icon1.png', '512', '512', ''),
('imgd9c4409b06d05768efcdd18cb211c8c1', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'item', 'IMG_20190724_104642_033.jpg', '800', '800', ''),
('imgda0b1b19b7f8c1c2cc0f266034d0ef69', 'banb0b338e97d3243927fb3b1df35632749', 'banner', '2.jpg', '3448', '4592', ''),
('imgda18ec34c6cc39f5644c2f793ca74d46', 'itm_709970eef73e104ca5d4114b2485355d', 'item', 'IMG_20190724_104714_981.jpg', '850', '850', ''),
('imgdad4978c80325d1b20dc86ccd236c6f2', 'model26cd1d7b29739880ae5cacb9989fb62d', 'model', 'bmwz8.jpg', '1664', '936', ''),
('imgdb796b93170faba228ae3b91ccb5213a', 'itm_6a9d75d302b4cd077c367e4c6c51c9ad', 'item', 'bmwm31.jpg', '940', '627', ''),
('imgdb7cf17f61f42b36e52897b5ca4fbd5d', 'cata497304389495ab7c262383923243180', 'category', 'phones_cover.png', '600', '400', ''),
('imgdbdc858d705478313c8dd786c6f039e6', 'itm_99a63da9f5cb9655a371c4a6dfd9f673', 'item', 'IMG_20190724_104645_206.jpg', '800', '800', ''),
('imgdc1dabb02366eabe355b6504b308eb20', 'itm_510c7390b14b1db1ab64823e00cfe2cc', 'item', 'bmw6004.jpg', '1920', '1080', ''),
('imgdce4725e1cf135bd370fd4d2a9d6d3a4', 'feed143913da363b12f35bb44d583561d95e', 'blog', 'a(6).jpg', '510', '340', 'old blog'),
('imgdcffbaefa4c65613444c764e1aff3e89', 'abt1', 'about', 'about_wp.png', '600', '400', ''),
('imgde46820970b56c2c3e818a54c44ef9b5', 'subcat0132c5a6635b0a9d5c55278ebf226ad7', 'subcat_icon', 'services_icon2.png', '512', '512', ''),
('imgde92e3d63f7dfe45bd8783782f8beb16', 'subcat3c0d35feb4c9e82599bf6f8bfbd08405', 'subcat_icon', 'fashion_icon2.png', '512', '512', ''),
('imgde94c92a04727fcd6906849de7840270', 'itm_478864abde0d4c253f54cc5eb3a68619', 'item', 'IMG_20191007_121852.jpg', '4000', '3000', ''),
('imge0e7dacc9f7431b192ea76f0ae07d8f0', 'usr84264292f0b3360143f6ea8776b77f20', 'chat', 'Scan120190903141356.jpg', '1654', '2338', ''),
('imge173620c780cdf7da2fe7151be7791e0', 'model52842c22cf5d90d1dcaf40f10207a674', 'model_icon', 'bmw1.png', '512', '512', ''),
('imge1ea5145e9dd9fea378bb15771be3cd4', 'itm_a8bdffe7c03878acea4f6bb7095e6ecd', 'item', 'bmwz45.jpg', '590', '350', ''),
('imge5ef26f854a7ba04a156ec7407a32ae8', 'itm_b0b5113b2f31e962600ad49b74ed38ff', 'item', 'IMG_20190826_160932.jpg', '1200', '1600', ''),
('imge8a3add4c0d0df3c96e112865d0dc45a', 'catfa070dd5cc2a2c9c6196159f85480ff7', 'category', 'services_cover.png', '600', '400', ''),
('imge8fef8b0569935cf1f94a4d09dfd65cc', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'magazine-unlock-05-2.3.1390-_876D9B83618F47D0CFEDC5C03CA4FD4C20190704203357.jpg', '1440', '2560', ''),
('imge93936f9f5616734e194ae6d415188aa', 'itm_8c22c49be4fcb839586d508cb71bea62', 'item', 'magazine-unlock-01-2_3_2022-_f08c325f4670410fa5b19fafe141fd90.jpg', '1440', '2560', ''),
('imgea858e10667ce79e9fc6caf1d892ef8e', 'itm_7eccc7ea844c691ea7aae00efef07040', 'item', 'bmw6005.jpg', '1897', '1257', ''),
('imgeb8e2481b649d018c1b690261c690550', 'noti39045f4b1490d328c1b766c002438ff6', 'noti', 'Webp_net-compress-image_(14)2.jpg', '1280', '851', ''),
('imgebb18d86c2a6c66fe32051054b583cb3', 'feed143913da363b12f35bb44d583561d95e', 'blog', 'card_3.png', '600', '400', ''),
('imgec0da5c0ea7a3c586c2108fa9d3d7a1d', 'feeda03a4ef30100f631b4bed73193ab2074', 'blog', '1.png', '600', '400', 'desc'),
('imged144b7c7bf68d33ff616bed6943c0cf', 'usr1ffb51feddb5c60deeaacb5c644257c7', 'chat', 'Webp.net-compress-image (14)20190708000430.jpg', '1280', '851', ''),
('imgee8bbfeb67b13e2bb41265f9c6f73824', 'itm_2edb3a5e3bc2f616f930ca0b9d9da38b', 'item', 'FB_IMG_15638810741721.jpg', '720', '720', ''),
('imgef69fea8920229bf6a6664bcc7d5d33e', 'cate2f7ee7f7317ceddf4395f82665b5a86', 'category', 'fashions_cover.png', '600', '400', ''),
('imgef9c765448a477dbe54499afe8366564', 'itm_cd86448707196d5f0ea6b555e0da6561', 'item', 'IMG_20190724_104851_043.jpg', '1500', '844', ''),
('imgf01929cc2df8595f11e2764dff7200a7', 'itm_cbb02d168f9c3071567678dde6b94ac9', 'item', 'bmwz42.jpg', '1389', '908', ''),
('imgf1024753cce8128fdac6c522fe8aed80', 'model9f102e76d7d3b03b576f8bd57d665026', 'model', 'ta.jpg', '480', '319', ''),
('imgf2986ab78d2851307ea0506a7e3a83b8', 'feed46f2db96d64e82a65efeb0cff732563c', 'blog', '3.png', '600', '400', 'desc'),
('imgf4040b5b18fbc06ae4fbfd15bc08e4da', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'Webp.net-compress-image (11)20190704204545.jpg', '1280', '853', ''),
('imgf56de4f3f3b3bcb9078c7d344dd4091b', 'subcat0cc28d586f90805b3a569178b16b1fc6', 'sub_category', 'gamingpc.jpg', '1100', '513', ''),
('imgf5cb0bdcf7ea0f3ababb085c6eddf205', 'itm_b43fab1945f629fe579e1a06eb56e5dc', 'item', 'bmwm34.jpg', '650', '500', ''),
('imgf5e15a53395861927f472193039c37bf', 'itm_8c22c49be4fcb839586d508cb71bea62', 'item', 'magazine-unlock-05-2_3_1812-_d46ca9c9a81044a5a6b92aff9f750d29.jpg', '1440', '2560', ''),
('imgf5e2f5c22c29dc86e937cde38fa55622', 'cat3988c9afad482db9a872ee5c19c34fbb', 'category', 'music_cover.png', '600', '400', ''),
('imgf76c5e8da5f45ce88b2bc9de4bd2784e', 'itm_ee73165b7c94f2eff3a4201b9fe73c6b', 'item', 'IMG_20190724_104636_236.jpg', '1280', '854', ''),
('imgf784e8ecc94b84c70726e21423ba98b8', 'c4ca4238a0b923820dcc509a6f75849b', 'chat', 'magazine-unlock-01-2.3.1375-_E5A58282A4717F213DA893AC76D2EBB620190705004301.jpg', '1440', '2560', ''),
('imgf794497cdc55d027c606abb39f53e21b', 'itm_8c22c49be4fcb839586d508cb71bea62', 'item', 'magazine-unlock-05-2_3_1462-_ccb80bc03c46428bb4e61941ee0ff3732.jpg', '1440', '2560', ''),
('imgf8582695beb166e2525a4576fb28df88', 'itm_5058fb44b1f3a2efd9cbd0925ab67785', 'item', 'Screenshot_20190330-0004382.png', '720', '1280', ''),
('imgf860aa427501da8141b9098b282a60c5', 'itm_639636afdbe010ecbf64ae97d3fbf749', 'item', 'IMG_20190902_201432.jpg', '4000', '3000', ''),
('imgf902a64352e30f1c26f4aaf35482425f', 'itm_a0ee873a872f79828676b8850c3e875a', 'item', 'IMG_20190724_104829_492.jpg', '225', '225', ''),
('imgf93ed9a09b40f40a7f2913c59db08859', 'manu27aad03ae2ca70c3e527e6838c6ae86c', 'manufacturer', 'tesla.jpg', '1280', '720', ''),
('imgf9e47d100159755f9a0fb2345df03478', 'itm_80277b3fe7993feaffaa1c77d5ef5d33', 'item', 'JPEG_20190902_153204_250946971.jpg', '3456', '4608', ''),
('imgfb46d0b8a4ecb3458b570b844914c57a', 'cat3ed818f44f3a3e3d49a2798b4c51f5b8', 'category-icon', 'properties_icon.png', '512', '512', ''),
('imgfb4a73d2ff06e92c4a9386ab717c2771', 'subcat68bf2c365c88382966dd72a347fc3473', 'subcat_icon', 'services_icon1.png', '512', '512', ''),
('imgfba3f1a01f95e4f7ed3c8cc51db50931', 'subcatcdf3f568c7dedef69f2aa372e544a3d7', 'sub_category', 'sweater2.jpg', '223', '226', ''),
('imgfc10bd93694ad3a091c47668370722c1', 'feedccaca598699e47429206ffdcfb970e9e', 'blog', 'food_6.png', '600', '400', ''),
('imgfe01387ec18e741d396c7cf0bbee2c9a', 'model8d62068148a2b2526fedbda4eee826e8', 'model_icon', 'bmw2.png', '512', '512', ''),
('imgffe4c85b36de066ca159fcaeb172f396', 'itm_0580d078a65d3887457b4941033ce226', 'item', '10-28-17-noan72.png', '696', '522', ''),
('imgffeb0645a0ba0a619900a19cb2448fcf', 'itm_96e0467a73c02f861d0609003a528d90', 'item', 'bmwx32.jpg', '600', '400', '');

-- --------------------------------------------------------

--
-- Table structure for table `core_menu_groups`
--

CREATE TABLE `core_menu_groups` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `group_icon` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_menu_groups`
--

INSERT INTO `core_menu_groups` (`group_id`, `group_name`, `group_icon`) VALUES
(1, 'Entry', 'fa-pencil-square-o'),
(3, 'Reports', 'fa-list-alt'),
(4, 'Users Management', 'fa-users'),
(5, 'Miscellaneous', 'fa-plus-square'),
(6, 'Setting', 'fa-cogs');

-- --------------------------------------------------------

--
-- Table structure for table `core_modules`
--

CREATE TABLE `core_modules` (
  `module_id` int(11) NOT NULL,
  `module_name` varchar(255) NOT NULL,
  `module_desc` text NOT NULL,
  `module_icon` varchar(100) NOT NULL,
  `ordering` int(3) NOT NULL,
  `is_show_on_menu` tinyint(1) NOT NULL,
  `group_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_modules`
--

INSERT INTO `core_modules` (`module_id`, `module_name`, `module_desc`, `module_icon`, `ordering`, `is_show_on_menu`, `group_id`) VALUES
(1, 'manufacturers', 'Manufacturers', '', 20, 1, 1),
(2, 'models', 'Models', '', 20, 1, 1),
(3, 'contacts', 'Contact Us Message', '', 10, 1, 3),
(4, 'system_users', 'System Users', '', 10, 0, 3),
(5, 'registered_users', 'Registered Users', '', 10, 1, 4),
(6, 'abouts', 'About & Setting', '', 10, 1, 6),
(7, 'notis', 'Push Notification', '', 40, 1, 5),
(8, 'analytics', 'Analytics', '', 30, 1, 0),
(9, 'dashboard/exports', 'Export Database', '', 50, 1, 5),
(10, 'apps', 'APP Setting', '', 20, 0, 6),
(11, 'colors', 'Color', '', 15, 1, 1),
(12, 'popularitems', 'Most Popular Cars', '', 21, 1, 3),
(13, 'items', 'Cars', '', 18, 1, 1),
(14, 'fueltypes', 'Fuel Type', '', 15, 1, 1),
(15, 'api_keys', 'Api Keys', '', 10, 1, 6),
(16, 'apis', 'API Setting', '', 20, 0, 6),
(17, 'buildtypes', 'Build Type', '', 15, 1, 1),
(18, 'ratings', 'Most Rating Users', '', 18, 1, 3),
(19, 'sellertypes', 'Seller Type', '', 15, 1, 1),
(20, 'transmissions', 'Transmissions', '', 15, 1, 1),
(21, 'banners', 'Home Banner', '', 15, 1, 6),
(22, 'item_conditions', 'Condition', '', 15, 1, 1),
(23, 'versions', 'Version', '', 10, 1, 6),
(24, 'item_currency', 'Currency', '', 15, 1, 1),
(25, 'item_price', 'Price', '', 15, 1, 1),
(26, 'item_type', 'Type', '', 15, 1, 1),
(27, 'blogs', 'Blog', '', 10, 1, 1),
(28, 'item_reports', 'Reported Cars', '', 10, 1, 3),
(29, 'app_settings', 'App Setting', '', 20, 1, 6),
(30, 'item_locations', 'Location', '', 15, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `core_permissions`
--

CREATE TABLE `core_permissions` (
  `user_id` varchar(255) NOT NULL,
  `module_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_permissions`
--

INSERT INTO `core_permissions` (`user_id`, `module_id`) VALUES
('c4ca4238a0b923820dcc509a6f75849b', '1'),
('c4ca4238a0b923820dcc509a6f75849b', '10'),
('c4ca4238a0b923820dcc509a6f75849b', '11'),
('c4ca4238a0b923820dcc509a6f75849b', '12'),
('c4ca4238a0b923820dcc509a6f75849b', '13'),
('c4ca4238a0b923820dcc509a6f75849b', '2'),
('c4ca4238a0b923820dcc509a6f75849b', '4'),
('c4ca4238a0b923820dcc509a6f75849b', '5'),
('c4ca4238a0b923820dcc509a6f75849b', '6'),
('c4ca4238a0b923820dcc509a6f75849b', '7'),
('c4ca4238a0b923820dcc509a6f75849b', '8'),
('c4ca4238a0b923820dcc509a6f75849b', '9');

-- --------------------------------------------------------

--
-- Table structure for table `core_reset_codes`
--

CREATE TABLE `core_reset_codes` (
  `code_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `code` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_reset_codes`
--

INSERT INTO `core_reset_codes` (`code_id`, `user_id`, `code`) VALUES
('rcode0b8ef61fa58cf590d040daf67af9e33c', 'usr8b51d3a5612079dfd72cc2236da61b6f', '5808c5c832a4be1e55d60b46d9b99157'),
('rcode321259749a56ce2b90ccfeb5f8de5734', 'usrcddb58685b9c357d9b32588c4ee32197', '1c5db88d71a4a214586ed4a81adc717d'),
('rcode711c9c0ebf133cd3c84db095c30dde05', 'usr4edb805ca791dc7677ba4a785873debd', '929083c8f6a575b051d34d5dffe655cc'),
('rcodee8646229135ac9b9147e71d3c9871a58', 'usr84db7485fc9b8eeb0c74ed43fa8af040', '5a476af5bbe7807b66a9c42d69f3076f'),
('rcodef672b6c623dfe1e8e37d16cbaa573810', 'c4ca4238a0b923820dcc509a6f75849b', '9422576d11d16f4d1d2b1d063a22ef9b');

-- --------------------------------------------------------

--
-- Table structure for table `core_roles`
--

CREATE TABLE `core_roles` (
  `role_id` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `role_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_roles`
--

INSERT INTO `core_roles` (`role_id`, `role_name`, `role_desc`) VALUES
('1', 'admin', 'Administrator'),
('2', 'editor', 'Editor'),
('3', 'author', 'Author'),
('4', 'normal', 'Normal');

-- --------------------------------------------------------

--
-- Table structure for table `core_role_access`
--

CREATE TABLE `core_role_access` (
  `role_id` varchar(255) NOT NULL,
  `action_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_role_access`
--

INSERT INTO `core_role_access` (`role_id`, `action_id`) VALUES
('1', 'add'),
('1', 'ban'),
('1', 'delete'),
('1', 'edit'),
('1', 'module'),
('1', 'publish'),
('2', 'add'),
('2', 'delete'),
('2', 'edit'),
('2', 'publish'),
('3', 'add'),
('3', 'edit');

-- --------------------------------------------------------

--
-- Table structure for table `core_sessions`
--

CREATE TABLE `core_sessions` (
  `id` varchar(128) NOT NULL,
  `ip_address` varchar(45) NOT NULL,
  `timestamp` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `data` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_sessions`
--

INSERT INTO `core_sessions` (`id`, `ip_address`, `timestamp`, `data`) VALUES
('70gcs72n54ur00fd9455vfnbhfehijkd', '::1', 1574738307, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343733383330373b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('27fcv11egmprnfkvg2v59qqkklh7k3nk', '::1', 1574739470, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343733393437303b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('g8mpn0ufggto8uoais8q59ofms3u0nka', '::1', 1574742591, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734323539313b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('aguodsrkkf3m9686upkpbr9aio2ed94v', '::1', 1574742918, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734323931383b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('i6rkk3ijung5rlbe8d7e7dnebobf80oq', '::1', 1574743265, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734333236353b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('ve2uph4l6fupcgbeqer8u245cd7jmlvj', '::1', 1574743721, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734333732313b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('lp5jtmel954llgapecab8dad5t31babq', '::1', 1574744108, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734343130383b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('siuklha17ps4qp11e25qli0laddv2vsp', '::1', 1574744422, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734343432323b),
('ledo12d31vbcg1g9jt0kc4cs2keulljl', '::1', 1574744539, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734343533393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b737563636573737c733a34353a2252656769737465726564205573657220686173206265656e207375636365737366756c6c792064656c65746564223b5f5f63695f766172737c613a313a7b733a373a2273756363657373223b733a333a226f6c64223b7d),
('hpj0bqm54mvh8g4s5c66m1277oep3udg', '::1', 1574744899, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734343839393b),
('r73as1c7garm5kcb97lfe6gvdab51iek', '::1', 1574745200, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734353230303b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('a9idja9c6ous6qrgjfaafctlaq31cqkj', '::1', 1574745238, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734353233383b),
('pjk3seeifbp6md8p176kik54l9a3p5u1', '::1', 1574745507, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734353530373b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('vrc88rgvqcp2av3t1kk8tak071fk6kb2', '::1', 1574745713, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734353731333b),
('b8ipoee329gblok4r9g6feboia8ue7og', '::1', 1574745939, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734353933393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b737563636573737c733a33333a2243617220686173206265656e207375636365737366756c6c792075706461746564223b5f5f63695f766172737c613a313a7b733a373a2273756363657373223b733a333a226f6c64223b7d),
('h0sc3gimdtdeh80t5fsq1hm40pvandld', '::1', 1574746183, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734363138333b),
('rp16jk1sn4ioch8push65rbstdql514b', '::1', 1574746307, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734363330373b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('cd331qs9uidknsdie519d0e38s7kcsu9', '::1', 1574746625, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734363632353b),
('melo55i2gja96ru9sv302omdal0k7940', '::1', 1574746610, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734363631303b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('47oiq26h5jp9a771j6qhs93h5er98sqd', '::1', 1574746925, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734363932353b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('3jonislqii24cta5g3bs84f39eksok2o', '::1', 1574746989, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734363938393b),
('dot9pohfp8sfbp226sgk6d8l1eef61cs', '::1', 1574747280, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734373238303b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b737563636573737c733a33333a2243617220686173206265656e207375636365737366756c6c792064656c65746564223b5f5f63695f766172737c613a313a7b733a373a2273756363657373223b733a333a226f6c64223b7d),
('23uru5aii3te4uimvgj1ba9ih7fnkiep', '::1', 1574747276, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734363938393b),
('vi2b313pi4igqokmujt2qdthabfu03em', '::1', 1574748426, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734383432353b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('ihh02acg5otqnhog7t305tou28hlm13d', '::1', 1574749733, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343734393733333b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('2d11vihv6ob6tn3r5m8ceciid1cd4sss', '::1', 1574754153, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735343135333b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('cqijopld0mlc33klpvu7dsge154lnaho', '::1', 1574754611, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735343631313b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('u938kod27af3pc0p31uffi161vngvf5q', '::1', 1574754932, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735343933323b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643361366536653765656636643130376133323534343066336564626361313436223b747970657c733a343a22626c6f67223b737563636573737c733a33333a22426c6f677320686173206265656e207375636365737366756c6c79206164646564223b5f5f63695f766172737c613a313a7b733a373a2273756363657373223b733a333a226f6c64223b7d),
('2s8o8ioo8h775gt8rust8kkcenm4p6aa', '::1', 1574755319, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735353331393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643639363237313731393532323266643930396533346666326430386335613236223b747970657c733a343a22626c6f67223b),
('56b5a5sab89chckbfh1ic6mnmdnahv05', '::1', 1574755905, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735353930353b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643639363237313731393532323266643930396533346666326430386335613236223b747970657c733a343a22626c6f67223b),
('glgiijhd8jqmg9rt23l3eavi6uljmeht', '::1', 1574756224, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735363232343b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643262333063383761333731326461303630653761666533343663333934326661223b747970657c733a343a22626c6f67223b),
('gnk947002sn19bt9ea6fv499k2ie7t1b', '::1', 1574758260, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735383236303b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643262333063383761333731326461303630653761666533343663333934326661223b747970657c733a343a22626c6f67223b),
('la0hbs5s3fct6a24mc8e2p9nn4m9mljg', '::1', 1574758637, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735383633373b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c6363613734386135656137316662643564303137663664326266326661333664223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643262333063383761333731326461303630653761666533343663333934326661223b747970657c733a343a22626c6f67223b),
('u7magt9vj1sr8pi66bemel72vkpqmf5i', '::1', 1574758632, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735383633323b),
('uvo5r2fn1mseq6ke980d2g2bni92psuk', '::1', 1574758944, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735383934343b),
('gejsbpnr2q5ve3f8j48vj7a0vhg4m3mt', '::1', 1574758953, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343735383935333b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c6363613734386135656137316662643564303137663664326266326661333664223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643262333063383761333731326461303630653761666533343663333934326661223b747970657c733a343a22626c6f67223b),
('pu86u0oppb3047dgrd7pbmfjgt8t6agp', '::1', 1574760794, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343736303739343b),
('g90a5h1ehir62ftph326mjvss06gsf89', '::1', 1574760935, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343736303933353b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c6363613734386135656137316662643564303137663664326266326661333664223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643262333063383761333731326461303630653761666533343663333934326661223b747970657c733a343a22626c6f67223b),
('bg261n7bqbq82jkntubvf2kfsuu0a8cd', '::1', 1574761395, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343736313339353b),
('q994po5dje3op6mqec2ii28huran4mur', '::1', 1574761987, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343736313938373b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c6363613734386135656137316662643564303137663664326266326661333664223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643262333063383761333731326461303630653761666533343663333934326661223b747970657c733a343a22626c6f67223b),
('grbj95d9p6tj4htbukoqtcsrtgoecuur', '::1', 1574761785, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343736313738353b),
('ir70jjnsbdsdilasfmbmqoefnhr65neo', '::1', 1574761785, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343736313738353b),
('o5bmgd5o1uj0delef8uc3b39ba46h48s', '::1', 1574762299, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343736323239393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c6363613734386135656137316662643564303137663664326266326661333664223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643262333063383761333731326461303630653761666533343663333934326661223b747970657c733a343a22626c6f67223b),
('v4c5lmnfi1s7eg65ffvfhanqj0ah0nrq', '::1', 1574762487, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343736323239393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c6363613734386135656137316662643564303137663664326266326661333664223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b706172656e745f69647c733a33363a22666565643262333063383761333731326461303630653761666533343663333934326661223b747970657c733a343a22626c6f67223b),
('fsbrkk40u8v2bieis97o32cq16knrp3b', '::1', 1574925537, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537343932353531393b),
('j8koo2gq1gfbmj0s0maoj8dgls0h0t7e', '::1', 1575013823, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031333735383b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('168himoe55k2idvk0tqlc8vf4jerib59', '::1', 1575016377, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031363337373b),
('t2bjb711c8ruuiev1vdirtnckvual3na', '::1', 1575016769, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031363736393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('aelsj4h51eckthlmue7mq1ho939g8krh', '::1', 1575017078, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031373037383b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b737563636573737c733a34303a224d616e75666163747572657220686173206265656e207375636365737366756c6c79206164646564223b5f5f63695f766172737c613a313a7b733a373a2273756363657373223b733a333a226f6c64223b7d),
('5i3di80su6kk1cm3g3jkauf21dksmibo', '::1', 1575017601, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031373630313b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('fracrfrvno2jsqpkdd307k11eq72npec', '::1', 1575017919, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031373931393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3236636431643762323937333938383061653563616362393938396662363264223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('ngb4lnbmkglqdd1b10dsigrm4hsdscua', '::1', 1575018346, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031383334363b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3532383432633232636635643930643164636166343066313032303761363734223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('t4gnkk8ne0ihossd8iglmgb97sf896a0', '::1', 1575018200, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031383230303b),
('r7tt1h536hg3i9106ndcm647j6n3et07', '::1', 1575018631, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353031383334363b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3630326635336238343561646661623362313731326365363061323366333839223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('d1d124298b83d0772a31f0d90dc4883f63d1ef6d', '103.231.92.167', 1575020766, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303736363b),
('c5f6f187d0b8cb0b658401ebb52234054dc88a90', '103.231.92.167', 1575020064, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303036343b),
('f4204082cb2e319b084e9c69b4591fb33299d3b9', '103.231.92.167', 1575020065, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303036353b),
('4739eb14f3d582bacf00241700d61b1c1797a6af', '103.231.92.167', 1575020065, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303036353b),
('cfa36d4ceedc6af6ff5783ff1c315220d9e1da9f', '103.231.92.167', 1575020065, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303036353b),
('d94be98bd6010b5262787ae232500f8d302529eb', '103.231.92.167', 1575020066, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303036363b),
('75560d8ef754ec000054d4df19e0c104949dabf1', '103.231.92.167', 1575020066, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303036363b),
('867b6d23a95fa2f1a8dcbfc5242f5e620c5ffb0f', '103.231.92.167', 1575020066, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303036363b),
('ec0fef535dc1538b77da7c78ca43dd0c70b00a86', '103.231.92.167', 1575020066, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303036363b),
('c8988ece5ba690bd77ecbf8316048801c849b103', '103.231.92.167', 1575020095, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303039353b),
('e0050658c768a84531df27a784795258d5321d7a', '103.231.92.167', 1575020103, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303130333b),
('d14f16dfaf114bea7fbbce742d0d332796d242f0', '103.231.92.167', 1575020115, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303131353b),
('68bd37835396bf0eb96584ad7f6ac3c92606ae3e', '103.231.92.167', 1575020115, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303131353b),
('977ae911c46fd7df101aa2556428ea747079e520', '103.231.92.167', 1575020116, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303131363b),
('88afca511e473ccb241085d7df7b568e0dd6d1c5', '103.231.92.167', 1575020116, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303131363b),
('bd3278a86705d0dd6fd65565016ccf767d281370', '103.231.92.167', 1575020116, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303131363b),
('dbca7e99c3f95c6378012c9ca1134fdf4a23ae49', '103.231.92.167', 1575020116, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303131363b),
('882360c334750adc775e1e4db3c6e974bf24cc6d', '103.231.92.167', 1575020117, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303131373b),
('3c224257158bafd963883728ff8c8927feb1fbc0', '103.231.92.167', 1575020117, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303131373b),
('19cf7782b38b61996881b17d0e6470cbf7c69fa1', '103.231.92.167', 1575020130, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303133303b),
('fe132e1be1807be2a36dc8e98397be0b1cab1c37', '103.231.92.167', 1575020147, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303134373b),
('26c337a1594b375dc4e9967e64b9cdcf5e11d268', '103.231.92.167', 1575020148, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303134383b),
('1e1171f94f4f60b867c455817ee24e0af93cfab9', '103.231.92.167', 1575020148, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303134383b),
('ad47f0221abbc2200e57645b66e8034b0b037500', '103.231.92.167', 1575020148, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303134383b),
('a14ebaf9e1ca08943c0e223962836296ca8663ea', '103.231.92.167', 1575020149, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303134393b),
('57696689610bc04e8828421b1194c7f90c4f8f02', '103.231.92.167', 1575020149, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303134393b),
('3b01b54c310d24607d5bae00229c9670db2bd0ae', '103.231.92.167', 1575020150, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303135303b),
('6082294202d5d6080427a9ec375cfee931943f94', '103.231.92.167', 1575020150, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303135303b),
('b1b6aa4cb411bf44e80b213cb0d455ac7c575b95', '103.231.92.167', 1575020178, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303137383b),
('ddf13701839e0a8f0ae67247d05df64c6c8472a2', '103.231.92.167', 1575020181, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303138313b),
('d742fa76fde9296ce526d11d695fe95fbc260430', '103.231.92.167', 1575020181, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303138313b),
('1cc0d0a9e28e2bef3eede1eda84904332e4dd03b', '103.231.92.167', 1575020182, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303138323b),
('5f91bf3f67ecae26218f10b1fca6f3e680b38c90', '103.231.92.167', 1575020183, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303138333b),
('7e3cd69a3e2ed5c90a6210f5e0b72c1923364943', '103.231.92.167', 1575020183, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303138323b),
('6a5b396a47caefb64289c1793a7c106dde441677', '103.231.92.167', 1575020183, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303138333b),
('34488e10abd9704686702bfd1978f482c4e66443', '103.231.92.167', 1575020183, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303138333b),
('0840abba6315d9ef3e434bbb8bfa56f20f8ec7f4', '103.231.92.167', 1575020191, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303139313b),
('f0a58f6b236031f8e572c87da3821fee75261e13', '103.231.92.167', 1575020200, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230303b),
('33a347e5b0f2a7f42944c85aa9fd8515ecb22385', '103.231.92.167', 1575020201, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230313b),
('10c4915a1a52e8b22c1c03fe4347978c036e432f', '103.231.92.167', 1575020202, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230323b),
('4ff6be54230917d2721444332d71386a3ab65af9', '103.231.92.167', 1575020202, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230323b),
('881824f3fe39271376ada91e6e7f945fefe338bb', '103.231.92.167', 1575020202, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230323b),
('e933f5fcc8018d4645c4f05d20efcc5f7024ebe7', '103.231.92.167', 1575020203, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230333b),
('e29060db08e8766a95616a37db194170fc9c76f8', '103.231.92.167', 1575020203, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230333b),
('6bbac6f2738cebd77ba6d5be0948f6c1aec2d568', '103.231.92.167', 1575020204, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230343b),
('2c785adfabb54cde45bea7f45e58af223703ef27', '103.231.92.167', 1575020209, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303230393b),
('bb3d894fe61f159a2433ca9880d45553c28e75fa', '103.231.92.167', 1575020215, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303231353b),
('ff46443260a56c955d70e799d48504114b4c7016', '103.231.92.167', 1575020717, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303731373b),
('c89520539e17731281497e5cb6f922623dddba20', '103.231.92.167', 1575020719, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303731393b),
('b65eb4db155ac9b13e3bc0a38ee2527ad76cca2c', '103.231.92.167', 1575020719, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303731393b),
('170704be22bf50cc7cb558f29a31cff47d7176f2', '103.231.92.167', 1575020719, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303731393b),
('964f19ef22af68d844b6ac3100571bea9dab2fbe', '103.231.92.167', 1575020719, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303731393b),
('03e130ba11658074efc2b7911bac3a503324be23', '103.231.92.167', 1575020719, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303731393b),
('a1407ab815a26084a9fe65fff57e7311337d5c8e', '103.231.92.167', 1575020719, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303731393b),
('ab2e3283f6505d7646e57f35a354e2a09bb4be8b', '103.231.92.167', 1575020719, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303731393b),
('9e7b36de773301e927089c054f009271ebb3b759', '103.231.92.167', 1575020721, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303732313b),
('d72f90c12ba557c461a2e29b2b80772647e5f6c1', '103.231.92.167', 1575020723, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303732333b),
('21a90328789fb79467533f38b3513f980f3b2fd9', '103.231.92.167', 1575020724, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303732343b),
('dedb4eb0eead4d5883374e676cbc213ccb0fdcd3', '103.231.92.167', 1575020724, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303732343b),
('71e7bfd4ba184831e9d5af644dfb642cce0f7880', '103.231.92.167', 1575020725, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303732353b),
('98c93e4da8face431238814a5df019e386c43bbe', '103.231.92.167', 1575020725, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303732353b),
('0df305b6ffb32305642866fdc81ecbcdc07a8bc5', '103.231.92.167', 1575020725, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303732353b),
('56f63fb1abde8a9e6850fe3ea718102d0863e162', '103.231.92.167', 1575020725, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303732353b),
('cabae17b736de599daf64bd6e96caddc677b285d', '103.231.92.167', 1575020745, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303734353b),
('3c800a159ebcb44811c8e0ec2459fc8c242af40b', '103.231.92.167', 1575020746, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303734363b),
('1c98c761f2bc416056dd001b37b00019c5c63a89', '103.231.92.167', 1575020746, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303734363b),
('9e6ae736b9c93e703c3c60f553048e6cf36ac673', '103.231.92.167', 1575020746, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303734363b),
('e615f2c6445f62022c85582f4185fc11b95f2ab7', '103.231.92.167', 1575020746, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303734363b),
('46ad58d060e859c2921c1f733e8522f7799ad7b6', '103.231.92.167', 1575020746, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303734363b),
('cda014c1cfdef11ebd0460a7b2f283ba16e4c0d8', '103.231.92.167', 1575020746, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303734363b),
('e888d1086511f5196423e1dc3086b1b23027cd7f', '103.231.92.167', 1575020747, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303734373b),
('f6be8269c6e4004e27a4f8063e49991c1da05cb7', '103.231.92.167', 1575021068, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313036383b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3532383432633232636635643930643164636166343066313032303761363734223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b737563636573737c733a33333a2243617220686173206265656e207375636365737366756c6c792075706461746564223b5f5f63695f766172737c613a313a7b733a373a2273756363657373223b733a333a226f6c64223b7d),
('ff099e8a60f03a81509e473305897de11f1fc385', '103.231.92.167', 1575020789, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303738393b),
('7b3532217ef73a364077e18cd92a37c0c5f443ed', '103.231.92.167', 1575020791, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303739313b),
('20e20b88b82e8dcf869cd1a5cbd25a8382f35ac0', '103.231.92.167', 1575020791, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303739313b),
('930a0e4827d9a4356bd698b4a4a9c358f7aed2d1', '103.231.92.167', 1575020791, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303739313b),
('80ec810666da7f63d963e0177955dff488fdc94d', '103.231.92.167', 1575020791, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303739313b),
('1586ce373a35a36a0e2ccf7071b164c41e982336', '103.231.92.167', 1575020792, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303739323b),
('a20463d009ba39e0e27cb68d4cebc0bed2768383', '103.231.92.167', 1575020793, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303739333b),
('4dee56d1375f51906ae1b72aff33e75a04e99b9f', '103.231.92.167', 1575020794, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303739343b),
('32392107cf2a7dad12e85952cf6414a2062e3407', '103.231.92.167', 1575020845, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303834353b),
('457aae28e1b53a5d0a92bdf6bd4806050b1584ae', '103.231.92.167', 1575020846, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303834363b),
('fc3ea155708a8c61c35df517ac58380347bf8317', '103.231.92.167', 1575020911, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931303b),
('ff888b3ca61ba60a46a23aa182bf00b0d59a1867', '103.231.92.167', 1575020912, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931323b),
('f7666fd799d78993185367f9725f4e6600b8f522', '103.231.92.167', 1575020914, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931343b),
('cc375c65be8dd023cb365ecdcb103c3a0d99da93', '103.231.92.167', 1575020914, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931343b),
('e9b1a990a919cacbec487b484f7eb325481472dc', '103.231.92.167', 1575020914, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931343b),
('bc9fed04da57e224f8013889c252afecbeabdc79', '103.231.92.167', 1575020914, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931343b),
('652b06a8688836ca69993a7fb34cb8efcb3a1567', '103.231.92.167', 1575020914, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931343b),
('723550d4a261987df890a20fc2ffecb57e3d5fe6', '103.231.92.167', 1575020914, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931343b),
('bea120242fa129cf6ab22b4b044ce90e76dab7e9', '103.231.92.167', 1575020914, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303931343b),
('13622b4703b579f94ccf5fc7c3ba9fa72f0d0f30', '103.231.92.167', 1575020924, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303932343b),
('55733362d0bec7d85f3c33b7c9701c9a8867b4fc', '103.231.92.167', 1575020925, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303932353b),
('97d9289902ad54d3426e71bc4c849af241ab448f', '103.231.92.167', 1575020925, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303932353b),
('e2edc5fb5f26beb5c59bc95fcdfa86c8eda469a1', '103.231.92.167', 1575020925, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303932353b),
('9a1abd7846ffdcff9dc7cd254b5819de133fc22f', '103.231.92.167', 1575020925, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303932353b),
('edd22c18edbe8966fc046a99ffb6be7d8fd42e6a', '103.231.92.167', 1575020925, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303932353b),
('9db0ad8e5d77e043f919189dbb6de2006a3671b0', '103.231.92.167', 1575020925, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303932353b),
('e9a95c482bf5fe51ad1aa64c2b9702b8f86177f3', '103.231.92.167', 1575020925, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032303932353b);
INSERT INTO `core_sessions` (`id`, `ip_address`, `timestamp`, `data`) VALUES
('e07246dd8b131e3a4bd862d3911c811cb28ecb09', '103.231.92.167', 1575021069, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313036383b7365617263687465726d7c4e3b6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b73656c65637465645f6d616e7566616374757265725f69647c733a33363a226d616e756161613462346462383964373135303634333632316463616630343364646437223b6d6f64656c5f69647c733a33373a226d6f64656c3532383432633232636635643930643164636166343066313032303761363734223b6974656d5f70726963655f747970655f69647c733a313a2230223b6974656d5f747970655f69647c733a313a2230223b6974656d5f63757272656e63795f69647c733a313a2230223b636f6c6f725f69647c733a313a2230223b6675656c5f747970655f69647c733a313a2230223b6275696c645f747970655f69647c733a313a2230223b73656c6c65725f747970655f69647c733a313a2230223b7472616e736d697373696f6e5f69647c4e3b7374617475737c4e3b),
('0bb8418fe916de3c0fa5779efbcdc088657e8f7b', '103.231.92.167', 1575021911, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313931313b),
('46b4908a74ac479763ecb688a77bd52073c92013', '103.231.92.167', 1575021919, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313931393b),
('626396e68fc52248928bcc7cf8c96de3003598ba', '103.231.92.167', 1575021920, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313932303b),
('def6b1a57f5d62c0e0791d6460d26fb03c627fe1', '103.231.92.167', 1575021924, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313932343b),
('f711dbd2d21100c0ee93f3049e170f1411cf21ae', '103.231.92.167', 1575021926, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313932353b),
('10a1467d7e187fa52f12acd794d5a40bd15ba26d', '103.231.92.167', 1575021930, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313933303b),
('cd43ef9d27eff58a95c7487efe2e3efaf8c85735', '103.231.92.167', 1575021932, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313933323b),
('99d7ae3b0aca5b58331d5aa9ad7acea881384250', '103.231.92.167', 1575021939, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313933393b),
('00726cd132aeba9a3b307b3c3dc9ce2862eed885', '103.231.92.167', 1575021948, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313934383b),
('0a63ae4a4c29041b65f87ddd370a43e53585bade', '103.231.92.167', 1575021953, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313935333b),
('06b2a35b20265b14f819fa3a3ae93203751c101d', '103.231.92.167', 1575021965, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313936353b),
('7d3fc57331db542aad7434167bf6dbec9af4de2e', '103.231.92.167', 1575021973, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313937333b),
('4950d89a76204b166b09a97cecb99c20366e6de7', '103.231.92.167', 1575021983, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313938333b),
('caad2a581ce44fbed41de568f8039f8eb7092e58', '103.231.92.167', 1575022253, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032313939343b),
('07872e01267862da5c994ea4722a9af4a48c1fe9', '103.231.92.167', 1575022020, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323032303b),
('a65f1c8638f66dd998eab8546fdb007417de8654', '103.231.92.167', 1575022020, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323031373b),
('82f8cb00b7af744b28ec1737ffcb53d347dec364', '103.231.92.167', 1575022032, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323033323b),
('71a0b73b5b3bcfbc73694aa2f20670b2a46bc020', '103.231.92.167', 1575022051, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323035313b),
('55406b0240d69e2f289445dc16c0db4238f4f13c', '103.231.92.167', 1575022055, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323035353b),
('79b79b8ca4d535182590fbc246c0237d0f7aaa2e', '103.231.92.167', 1575022055, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323035353b),
('f1cb41d7f7d8fca31ebc830fd94d370fb2d6733d', '103.231.92.167', 1575022056, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323035363b),
('46d505b59f5b59acfa05db07c9f91ddc1b729c1c', '103.231.92.167', 1575022064, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323036343b),
('f728df41d9f4d8fb146871a619001ab350ae43eb', '103.231.92.167', 1575022064, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323036343b),
('db1f02b6a7730bd65228b340ddabb9a58ad60231', '103.231.92.167', 1575022065, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323036353b),
('d2399a00839b6e00d8d6e2292aa17b5763b87ade', '103.231.92.167', 1575022065, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323036353b),
('844bc1defbd4261b41164f587a844e93a5d8d166', '103.231.92.167', 1575022066, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323036363b),
('9a02df46eeeca16d4c17b6003c3497882d4d3f1c', '103.231.92.167', 1575022066, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323036363b),
('d0d450b98abd360aa63f1364538a79c5e874f3b0', '103.231.92.167', 1575022069, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323036393b),
('191f89297a23d2237678e1ae0a28e14ae816bd1a', '103.231.92.167', 1575022088, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323038383b),
('61b3403666f01d97e3c8980df66ffa550d06841b', '103.231.92.167', 1575022091, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323039313b),
('d1f205bdd14e3734fb4fdb6a26030771fd7ea987', '103.231.92.167', 1575022093, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323039333b),
('ec7162da08e7f5d84a5d5425640eca1cb190bf45', '103.231.92.167', 1575022094, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323039333b),
('eda78e4dcfdd6374b81295e065225dc8df753311', '103.231.92.167', 1575022094, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323039343b),
('b9c31d957633f35fc4a4c7814edca4aff49a95bb', '103.231.92.167', 1575022094, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323039343b),
('8646ec3af7d25c1a9547d4e7d847dbca5770b048', '103.231.92.167', 1575022094, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323039343b),
('d92790e8c9ef7e930751d21555e430ad57b77d9a', '103.231.92.167', 1575022094, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323039343b),
('27738bf4cd3acfb22a95c3bb21d56866fe8fb544', '103.231.92.167', 1575022138, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323039393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('b2a24e71f4a683cecfe30b834b32502a709af7e4', '103.231.92.167', 1575022263, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323236333b),
('19eb8b0947f0344ea56f3304541e725972065b20', '103.231.92.167', 1575022269, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323236383b),
('ef75d78924d54ef4ee98eddf3ba244847a7558ae', '103.231.92.167', 1575022270, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323237303b),
('78242f6aec4bde969f05df37473e0fbc8ad1e79f', '103.231.92.167', 1575022270, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323237303b),
('b1d305c22a67b9821f2da8380ac261f74dbd15f8', '103.231.92.167', 1575022270, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323237303b),
('68d7b09bb3b05db02b8fb99a3e449a662316d8a7', '103.231.92.167', 1575022271, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323237313b),
('837c7711f2d88d7348f8a67f1a8bef411fc22e44', '103.231.92.167', 1575022272, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323237323b),
('4dc13e303d577d07ba0143d74ce9a2196663f97a', '103.231.92.167', 1575022371, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323337313b),
('bf39864e851e1d8d53f522b0f66b339efb0f6201', '103.231.92.167', 1575022383, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323338333b),
('e9e586c601b1387fed6b0fd946bf43f4e914aa2c', '103.231.92.167', 1575022384, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323338343b),
('89c610f3202231245143a3a8dc0e6fa4d92d1938', '103.231.92.167', 1575022384, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323338343b),
('9833b11441537161bffd9ce9ce7e1a476f94e05d', '103.231.92.167', 1575022384, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323338343b),
('e765fb224e835a0254ae7c0d2e53856e2f3c2cfc', '103.231.92.167', 1575022392, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339323b),
('d31449db9db9c171e7a52c173fc0c65ec98ea851', '103.231.92.167', 1575022392, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339323b),
('2b8b4b6e2842fdfbdd2e3d7737b106a472df50b1', '103.231.92.167', 1575022392, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339323b),
('48afbca2ca32522725911e3b73f8c4877c98db7c', '103.231.92.167', 1575022392, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339323b),
('a4b87ab2c934df4e2172abeb29ef054f466f94e7', '103.231.92.167', 1575022393, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339323b),
('b1b7b2249831211bed579e158613f812e2d93430', '103.231.92.167', 1575022393, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339333b),
('e08a18ad94b34f497adf350bf6c091ddcc2d2009', '103.231.92.167', 1575022393, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339333b),
('085758077f960f56e68490ecc49e9592ce8abf36', '103.231.92.167', 1575022396, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339353b),
('afd23820ba4add0ebbb95bec8b7d8694b477373e', '103.231.92.167', 1575022398, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323339383b),
('d4f984027b6f80a092668de3786b759088cb7f6b', '103.231.92.167', 1575022421, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323432313b),
('c4b0e88138dc1b17dd70dc1e922bfda8ae9566bc', '103.231.92.167', 1575022423, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323432333b),
('24cfbc49b40ce3f9616c71558367ace1d24c96f5', '103.231.92.167', 1575022429, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323432393b),
('5b507d571b509d0000b6c0fbee5e289e1547b647', '103.231.92.167', 1575022432, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323433323b),
('9a582e7ed901906f16ee7c0537a9273911b39e6a', '103.231.92.167', 1575022435, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323433353b),
('6a58fee0827920f6f97b2e61b4096ca342976d98', '103.231.92.167', 1575022445, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323434353b),
('29654c38eab864fa6614c4082a336be5e371ef8a', '103.231.92.167', 1575022456, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323435363b),
('94ab67b29c87b04335537978848b7ec434fe5aa0', '103.231.92.167', 1575022458, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323435383b),
('6f5b115270a65251187daaf1327c128dc0a4bcb5', '103.231.92.167', 1575022462, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323436323b),
('7f1b3c6964c5317896b71d79cb933d24ed19027b', '103.231.92.167', 1575022466, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323436363b),
('d29f7818387608a5314a19c9b4307f7fda945cc0', '103.231.92.167', 1575022479, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323437393b),
('1ac61d49f8db7f7bc3c070a693060517de588711', '103.231.92.167', 1575022480, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323438303b),
('90bb4501afd22078e90dc353abde8953ffcf9c3c', '103.231.92.167', 1575022481, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323438313b),
('b23c3a9dfbb39c099aeed1483233a27de997d394', '103.231.92.167', 1575022484, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323438343b),
('340267c50dfcb98b0004c6be0bfe44bdb4084a08', '103.231.92.167', 1575022487, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323438363b),
('a61c1522ac3920ffef11563b6cdae39cce47748d', '103.231.92.167', 1575022493, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323439313b),
('58dec5fa2d90cf9dbb0ec6f1dd0b489d791f90eb', '103.231.92.167', 1575022493, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032323439313b),
('b444ce9cd06866c763b5ccb92134b9c97d47f609', '103.231.92.167', 1575023820, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333832303b),
('aca4a991850b89dfa64a91e25990155704942e88', '103.231.92.167', 1575023827, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333832373b),
('09a53929384ec0c092522ac372d38546d138fed4', '103.231.92.167', 1575023832, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333833323b),
('11ed85aa0d710f3689b3ad0a55a0eea5e041d1cb', '103.231.92.167', 1575023899, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333839393b),
('873d3cb3f1cb05d0c8403613626d36c1e29fc37c', '103.231.92.167', 1575023903, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333930333b),
('af617cca4dbedd4812a522b75eb8ab4885b94e3b', '103.231.92.167', 1575023903, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333930333b),
('5c50a79cc7742c6fa0a6eea40fec5ab5e7581649', '103.231.92.167', 1575023905, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333930353b),
('ddbc699506124d29facb579fc494940f18128ac8', '103.231.92.167', 1575023905, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333930353b),
('44da2f467ce80c6b70fde1895653c3c4d6efe17b', '103.231.92.167', 1575023905, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333930353b),
('097d562f9a3513d4e5bd677057ed669a658f2a3f', '103.231.92.167', 1575023906, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333930363b),
('d02d066c78d890073bd07d31e06d6bf63b62191a', '103.231.92.167', 1575023907, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333930373b),
('504f5892ee9b04cc813db47b0e732a892f69bdb3', '103.231.92.167', 1575023926, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333932363b),
('df04d582559f23e26a92d473fc0bc8df0d273399', '103.231.92.167', 1575023926, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333932363b),
('0659741bfd5de3ef4072d6fa8ac03808216d36a7', '103.231.92.167', 1575023930, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333933303b),
('f9f04c91e098f12570f18ba72ad8dfab8a666518', '103.231.92.167', 1575023933, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333933333b),
('5446802c9fa2b31c5479ce3d6e0dbea16557abb3', '103.231.92.167', 1575023939, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333933393b),
('5f11a348b47edb5b07b9ecce2dccdb8a289b479d', '103.231.92.167', 1575023942, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934323b),
('4ebf1d672dd6205fca70779c062dacd7d2b5997d', '103.231.92.167', 1575023944, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934343b),
('e630767fcf0674e6283bc8214f6ee4f7dc9c9df4', '103.231.92.167', 1575023946, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934363b),
('8cc938e90b1c0913eb3b58daa5da2d03929a6f33', '103.231.92.167', 1575023946, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934363b),
('778601cc12e5c8ed6ffcd16961c1ae4c6844515d', '103.231.92.167', 1575023946, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934363b),
('5542c70351b75b862e3638f348d42aeec45470a6', '103.231.92.167', 1575023946, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934363b),
('37987369d30bdeca117b0e2514a8e02161197ab3', '103.231.92.167', 1575023946, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934363b),
('f8996832c1973771e744cc87f9f5b44c0addb96d', '103.231.92.167', 1575023947, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934373b),
('e98406a0bcabe2df7d3a0e4e3dd4fbf49c357d28', '103.231.92.167', 1575023947, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333934373b),
('56f542610389b586f264d0b9df63e875c04bfea8', '103.231.92.167', 1575023954, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333935343b),
('0726cd3c0f5d10f9af6fdbf1f8aef25d69ae4b61', '103.231.92.167', 1575023954, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333935343b),
('438a0a0df6d08f3cda4aabb3ad1d1eecfb0e9be7', '103.231.92.167', 1575023954, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333935343b),
('433514f6d3f689c1e4392303bc3cb790640dc901', '103.231.92.167', 1575023954, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333935343b),
('ecbbad3c8be13330479293b58a9c688f5b85808c', '103.231.92.167', 1575023954, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333935343b),
('b4b9494219fda2de169f9cb786a3b80f4a1339df', '103.231.92.167', 1575023954, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333935343b),
('6e4845698c573afc33fd7a2c34508fa167e805f5', '103.231.92.167', 1575023957, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032333935373b),
('a7567880cc26d3519bd56cbcbe9aac2fd2f83876', '103.231.92.167', 1575024908, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343930383b),
('d6e75693f7d8d3b0b8b538baf25bca199b726844', '103.231.92.167', 1575024912, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343931323b),
('8c90af5f1326416fd71bfbd0aa7757946967c2df', '103.231.92.167', 1575024912, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343931323b),
('f704cf3039f250f202d9282443b7b66924b1fe4f', '103.231.92.167', 1575024913, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343931333b),
('bc9f4455d2b5e1a74420e12fe7ad967d2f186261', '103.231.92.167', 1575024913, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343931333b),
('d538077b288b8a775e6f00bd4359f27a98047ba1', '103.231.92.167', 1575024913, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343931333b),
('5628d794929902b4cbc4a258d8c0971786072cc2', '103.231.92.167', 1575024913, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343931333b),
('131146faff19689b92a8f8eb4b112cff6941238a', '103.231.92.167', 1575025026, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343935333b),
('f2a00e40039e731fad334d09a8df5cd1abd9aa53', '103.231.92.167', 1575024981, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032343938313b),
('662d3247d786ad49c7b0adfef18cd1a332cd0133', '103.231.92.167', 1575025016, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032353031353b),
('eda2d9efd945dfc6cddfa915b32b718cec145d03', '103.231.92.167', 1575025042, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032353034323b),
('464c675845fc9e1486acccdb784805753715bf06', '103.231.92.167', 1575025043, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353032353034333b),
('d30bb937f98e295b273bcc0f32d2e98eeb537546', '103.231.92.211', 1575260707, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303730373b),
('1d824aa8b649752c329454c227a3b3a0c8f0ad27', '103.231.92.211', 1575260709, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303730393b),
('e385a7bee1ea78dccc28b2ca38e70c10b960f7b9', '103.231.92.211', 1575260709, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303730393b),
('2b05fbb7b3d996d0a610fc5d0ad8772ca90b555b', '103.231.92.211', 1575260709, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303730393b),
('1238b2bd20efbeae56eb9318333ae583cfa9a99c', '103.231.92.211', 1575260709, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303730393b),
('5d50c9cdfd69e0755b8564572c880e7f58c4b3f0', '103.231.92.211', 1575260709, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303730393b),
('4818408a5ecaf519fadd90871b59b151dc2aaf6c', '103.231.92.211', 1575260710, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731303b),
('b88b460ad278dd5112dc87f5229d1592c6c3770e', '103.231.92.211', 1575260710, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731303b),
('38fef67461af48dd8419700587c1e5e860eb9779', '103.231.92.211', 1575260713, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731333b),
('d5975683288b83a472938d51404e8b7ccfce2872', '103.231.92.211', 1575260713, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731333b),
('b84b7fb23ba41223480e7daca2f3507136fd344d', '103.231.92.211', 1575260713, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731333b),
('b483d7f4d208020b749db9496e18b0bd9ce98f37', '103.231.92.211', 1575260714, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731343b),
('b471e41769103f1d501ce2cd031aa7d1968925bc', '103.231.92.211', 1575260716, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731363b),
('63b0217471daa8ab26a21c6385f51193f642a7b4', '103.231.92.211', 1575260716, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731363b),
('9c74a358624a6bddce5abe187db8a1a11b87ef13', '103.231.92.211', 1575260716, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731363b),
('54f2b0105ecabea0e9f8c1380afcb0abd29c0436', '103.231.92.211', 1575260716, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731363b),
('f5d62ee04ec86c9646712901fd48688dc68faa85', '103.231.92.211', 1575260716, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731363b),
('df16f79bd0811ef28f91b1ccabe6f92d173f83eb', '103.231.92.211', 1575260716, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731363b),
('ef340f068cd454794db0bba1c795fccac8d0d0c2', '103.231.92.211', 1575260717, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303731363b),
('9f5649cc338410568115869966127b70be872448', '103.231.92.211', 1575260720, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303732303b),
('ececcbf854c526728bc7ea83d038ca3285fa0dac', '103.231.92.211', 1575260724, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353236303732343b),
('91a65a209444574a444c4f3d599eb01d1a95ce45', '103.231.92.211', 1575284151, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135313b),
('cd520622ecd52b0e660ba577418867be4607cf25', '103.231.92.211', 1575284152, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135323b),
('ea07bcc086f16f76833286351f9a6f36302d55e3', '103.231.92.211', 1575284152, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135323b),
('bcb6a6cc968aacceb00db6dcd58f9169552631b5', '103.231.92.211', 1575284152, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135323b),
('353735c7008ddf52b50bc86eefbce5a59ed5dcaf', '103.231.92.211', 1575284152, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135323b),
('56ec56bbb1307d95ae3cdf6c7285a6f70c71a306', '103.231.92.211', 1575284152, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135323b),
('a397f80cb352a08b02eb26c5da9e6c58a77ab675', '103.231.92.211', 1575284152, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135323b),
('0f0f61b67c72b4326b0ad2988712cd742d88d04d', '103.231.92.211', 1575284153, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135333b),
('7f7eaea3dc50d25eea91955bee43d6865525a454', '103.231.92.211', 1575284154, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135343b),
('33091251503bb68b2cfcd73b72368032e5425cf6', '103.231.92.211', 1575284154, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135343b),
('7137c05736b0ab4e6f8665d53aaceeaa28ef79d9', '103.231.92.211', 1575284154, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135343b),
('6da051a14505cc260d701d11b547bd4bca491619', '103.231.92.211', 1575284156, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135363b),
('95ae603c25a998f757ea93d859b62381676f3732', '103.231.92.211', 1575284156, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135363b),
('bc8eea33696e27505ddcf0ba2638cbdafbc1341f', '103.231.92.211', 1575284156, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343135363b),
('85773a2e31c348a372e96458159a45f13f3c84f2', '103.231.92.211', 1575284163, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136333b),
('6e5751f0f0ba0418176104203b9f4311b54dce4f', '103.231.92.211', 1575284163, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136333b),
('00de7867a51ed6c3e7125b69d04c72c8067fcae9', '103.231.92.211', 1575284164, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136343b),
('8fe6c5117b83e493f19e1260407124e9afb165bd', '103.231.92.211', 1575284164, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136343b),
('885f1164d6e54f4da2734744e62170d712215648', '103.231.92.211', 1575284164, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136343b),
('d3e5dc15d67814e90acaf00bb849ba4c12359c08', '103.231.92.211', 1575284167, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136373b),
('66d919ee810fac43de8f96269f67750e3a804c4d', '103.231.92.211', 1575284167, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136373b),
('f49aca3631edc5cb97027209c54679cf0769112f', '103.231.92.211', 1575284169, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136393b),
('cd6eb10008cea975193958b30f0139dac6fb635e', '103.231.92.211', 1575284169, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343136393b),
('504862567686ff8b402903653ba5f118e85cfb6a', '103.231.92.211', 1575284170, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343137303b),
('599faf538b4b6511057f382f252f8924653f690a', '103.231.92.211', 1575284171, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343137303b),
('bbfa76478f429f2208619d47fbe8c12b14a5c93a', '103.231.92.211', 1575284172, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343137323b),
('b827a58761b6f8b46b29dcb252cb7f93cbafcdda', '103.231.92.211', 1575284172, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343137323b),
('73e538da90d19290ff8fc2e1757852b4c9f81484', '103.231.92.211', 1575284174, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353238343137343b),
('c0d5aef83c393513cf7365c5e6aea2d9528aa179', '89.133.250.216', 1575415079, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353431353037393b),
('86c13c60dcb95a559fe6322d85b9ff8a17e71d1f', '103.231.92.12', 1575527976, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353532373937363b),
('d5bc79aacb2f935d9c17888ba54f0e1684900bfc', '103.231.92.238', 1575603411, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353630333330383b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('01aadffb523c5a8d36ac5d6c3c638db0c7db5bb7', '186.236.167.144', 1575635018, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353633353031383b),
('e9b9159ae8d11311c3d78a937a16b1bff057e42d', '186.236.167.144', 1575637791, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353633373739313b6572726f727c733a33313a22456d61696c20616e642050617373776f726420646f206e6f74206d61746368223b5f5f63695f766172737c613a313a7b733a353a226572726f72223b733a333a226f6c64223b7d),
('b5dc3c785e72d95cef005253a4c6b98ce2c6526f', '186.236.167.144', 1575640249, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353634303234393b6572726f727c733a33313a22456d61696c20616e642050617373776f726420646f206e6f74206d61746368223b5f5f63695f766172737c613a313a7b733a353a226572726f72223b733a333a226f6c64223b7d),
('92ecbcc046c5ae1f2102ba3101f9e1f0c09d2253', '186.236.167.144', 1575640334, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353634303234393b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('b5073bd167fd4825d7f008c94c79722d65ad47bf', '103.231.92.101', 1575694328, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353639343332313b),
('3a7f3487a1c581826e537cef263d13aa5df312c0', '103.231.92.47', 1575863632, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353836333631313b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('088e432192c64ee6f57fb37335e74bbcd3e36b12', '103.231.92.36', 1575907613, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353930373631323b),
('61c05217b96712e8f0461dd4561a7776646562aa', '103.231.92.36', 1575946163, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537353934363136333b),
('d9ea11c5e0844015cb170b4b28224318fa37e964', '103.231.92.100', 1576050731, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363035303733313b),
('de8913139ffecdec3325e90d5fcb542d224a11be', '103.231.92.100', 1576050732, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363035303733323b),
('adb1466b6e26095d9295d9bf0e93afa943186492', '103.231.92.100', 1576050732, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363035303733323b),
('b31ce1a116102596817abf02012bca733023d13d', '103.231.92.100', 1576050732, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363035303733323b),
('97e5e2b76ad6d77ce617f8f1b56b25c2039db9ae', '103.231.92.100', 1576050733, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363035303733333b),
('635448247a246454983fa6c057954bd89835c241', '103.231.92.100', 1576050734, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363035303733343b),
('2b993734f72dfa308eaa358651f09c94ae8f0667', '103.231.92.100', 1576050734, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363035303733343b),
('fc63f2a926030186585747e64dde675084d3336b', '181.65.124.74', 1576090226, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363039303139353b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('8ac666ac48c0f5d2d6551483178f7c3dd7306014', '103.231.92.23', 1576128634, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383633343b),
('cedc6dd3d17c714048f7407d07cbe433866f55f6', '103.231.92.23', 1576128637, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383633373b),
('641e5697fe2a59f2a3307f53d542ca2441d7088a', '103.231.92.23', 1576128644, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383634343b),
('4d61b98e0138cb6a18a3970122ebe15f9cde300e', '103.231.92.23', 1576128644, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383634343b),
('027c7d9b45c40e428f6a72d9fc18fa3f60133bad', '103.231.92.23', 1576128645, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383634353b),
('973364008f5f1bcdcebb57ca42d676284a68a966', '103.231.92.23', 1576128645, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383634353b),
('296819c5def0142ac90f27d0eb0ecf3aecb292bb', '103.231.92.23', 1576128645, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383634353b),
('264e3d9319fbfebbcf398be41e4f0b317b4be15c', '103.231.92.23', 1576128645, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383634353b),
('41995c5e1d0ed6c4b7494dba441feb6756f8ef17', '103.231.92.23', 1576128646, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383634363b),
('25bd8c8dd63c501caaa221f4b23c38676b8f2ec1', '103.231.92.23', 1576128656, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383635363b),
('4acecc8629da781af2b4e46fb48651e194f7a775', '103.231.92.23', 1576128663, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383636333b),
('5118b9371c8279350e58119dce8a26ac80b87042', '103.231.92.23', 1576128672, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383637323b),
('49eb2ecf366b9737248a6fc83b5ef24781d28b90', '103.231.92.23', 1576128683, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383638333b),
('91bbaf0588e63aba63bc605af0c509b54343f150', '103.231.92.23', 1576128685, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383638353b),
('ddaa0aac9ffded1d00fa2508a7b9f41a6b978d0b', '103.231.92.23', 1576128702, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383730323b),
('dc4bd3e82ba7c72e8f06322f9e42e7d351496603', '103.231.92.23', 1576128707, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383730373b),
('5daee65ceb58dec42c9f5d1d897a683764128a34', '103.231.92.23', 1576128712, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383731323b),
('610fe2ce48516173d0ba3ec07aa01d3feb2cdc4a', '103.231.92.23', 1576128712, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383731323b),
('c9f324735a1d358fd7d6b2d07c6444c7bd4bc618', '103.231.92.23', 1576128715, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383731353b),
('21813e127abb7410e8ade1e427def0c4c1f1d2a8', '103.231.92.23', 1576128729, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383732393b),
('399d6a5681c292e1ca218cfbc7c2bd7e736e23cd', '103.231.92.23', 1576128729, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383732393b),
('8dfba232552332170448ca3ecd55249c2486d7cc', '103.231.92.23', 1576128745, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383734353b),
('fb1b689cd85b7970b81de4d82e023c17c3c778d4', '103.231.92.23', 1576128753, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383735333b),
('402cd53726ed2531c4926059abab151d64f01df0', '103.231.92.23', 1576128753, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383735333b),
('059ece94023eac4b429c7596025a86a1a0d71065', '103.231.92.23', 1576128756, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383735363b),
('550aab7a5c7694da2c3140d2ae88a19591d2e376', '103.231.92.23', 1576128766, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383736363b),
('0210d02a963e6f1d17709ac586e8cd66f70b3232', '103.231.92.23', 1576128769, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383736393b),
('29ec213760fdfbe6645d6919b29125d182792061', '103.231.92.23', 1576128779, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383737393b),
('83202765c5cd54e389de8112bcb47acac8a18b72', '103.231.92.23', 1576128780, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383738303b),
('3709921acb41ab6a5accd2227ef746c02ecc3f8d', '103.231.92.23', 1576128783, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383738333b),
('09b6f83991bfb907ecac446afef5b14f11a6a8eb', '103.231.92.23', 1576128786, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383738363b),
('ab59dc06fe6d44c5fcf8e9797227780f2cbaf079', '103.231.92.23', 1576128786, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383738363b),
('86f7e83bffdb1c1b8335e8855864d41a26e73c88', '103.231.92.23', 1576128791, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383739313b),
('76536f90f3ec1da0665dc9498ff5337855910208', '103.231.92.23', 1576128798, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383739383b),
('8423577ef0f9dae19ccdf0e247e24c8dd562e8c9', '103.231.92.23', 1576128857, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383835373b),
('8db3cb81adfa62a66e713e9d075dd85926f8d367', '103.231.92.23', 1576128938, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383933383b),
('dbb07a011bf336d761246054da39c826be507a49', '103.231.92.23', 1576128939, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383933393b),
('ce645c5db9f10fb12a43d30dde32673c231439f9', '103.231.92.23', 1576128945, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383934353b),
('201b4b49942ca52fdcf6eff16b3752393268243b', '103.231.92.23', 1576128946, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132383934363b),
('997a9d4ef695c6bd96f402792fffab0edc04761b', '103.231.92.23', 1576129334, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393333343b),
('cb5e5e49b0872fe75966a2236694bb013bf07621', '103.231.92.23', 1576129337, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393333373b),
('fb1ba4d9241096bb672bf39e5fd38df2f725a4c6', '103.231.92.23', 1576129342, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393334323b),
('6c32ee4bca363b080abecc476e605d5b4615624d', '103.231.92.23', 1576129342, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393334323b),
('56148b99b4c1e00d9916edf53c3311b35a26270e', '103.231.92.23', 1576129344, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393334343b),
('e3354bce4793af597715ad69810d681ca5ae6fd2', '103.231.92.23', 1576129354, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393335343b),
('2c4e1894d80b5c44dda8a4f69a4aadc34e0cd5e0', '103.231.92.23', 1576129355, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393335353b),
('a9d8edc75a388719e2a76150e9a1e08a50710737', '103.231.92.23', 1576129363, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393336333b),
('f446be83dc0202d13ac9406ee6c8e6e544af3831', '103.231.92.23', 1576129363, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393336333b),
('5f9ff38e6b04e25e9e4dde0a07b6728bb5eb73a6', '103.231.92.23', 1576129367, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393336373b),
('822d8f70c1f6a98c15fb3c776cddfcdce30aae04', '103.231.92.23', 1576129368, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393336383b),
('bbd815bb11abca45a9a7e42ea983cd6fbceee2bb', '103.231.92.23', 1576129369, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393336393b),
('24024bc2bfd37d5972b223dd7d4af07b5d023c00', '103.231.92.23', 1576129389, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393338393b),
('6edd8a8b314e546e8a7334055d5aa8bb76bd2e5b', '103.231.92.23', 1576129602, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363132393630323b),
('480025e28b61bb8ffc45becd096b094501a98bd2', '103.231.92.23', 1576130999, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133303939393b),
('4c548e4a223206f71d90fb9740757c93e761fe17', '103.231.92.23', 1576131002, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133313030323b),
('2722aef20de532e05a9181c42e9b1efdc53c7de9', '103.231.92.23', 1576131003, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133313030333b),
('9dff887c1cd5723f785b90f1d9097113f5a55c94', '103.231.92.33', 1576134050, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343035303b),
('cb027e7693367a441f074ca21716fb0f8f4f3aa9', '103.231.92.33', 1576134051, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343035313b),
('98a576d08a70da0ca573c0265d5da2f331dc80ca', '103.231.92.33', 1576134051, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343035313b),
('2c08a4528c8b8fd1f0008069ee85a4832f143d1c', '103.231.92.33', 1576134051, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343035313b),
('1284bc74ddb29b25a86911677f23bb6ffb392f71', '103.231.92.33', 1576134051, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343035313b),
('b3e191fba5a374d7a2e22f5a5c312807c1e1080f', '103.231.92.33', 1576134051, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343035313b),
('312f7e2767ed936dba66f90eccf7dfd0786d16f3', '103.231.92.33', 1576134051, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343035313b),
('2fb1a3143d3f20cf764900237956c7c4813b3788', '103.231.92.33', 1576134078, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343037383b),
('d0f39ec7da01e6a435e61f4faacadc7d2d545e59', '103.231.92.33', 1576134078, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343037383b),
('3504be7860064e04e63be4aa8d7d0df138a1d475', '103.231.92.33', 1576134079, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343037393b),
('ef612188c9167685bf835e9731b1adc096a2a882', '103.231.92.33', 1576134079, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343037393b),
('4237128d811487bd00fb2ddc442be2231f03c1d7', '103.231.92.33', 1576134088, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343038383b),
('48870422836c5b696d5f7932413f519b9045fcdc', '103.231.92.33', 1576134088, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343038383b),
('aaa46eac3b0590f70074e4f4424665a320f9d8e0', '103.231.92.33', 1576134088, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343038383b),
('ffe3afceea65d2047ca506a6e737a54d7b04f087', '103.231.92.33', 1576134088, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343038383b),
('44f621a6d5596c107a12de69378aaad8cadf59a3', '103.231.92.33', 1576134088, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343038383b),
('693b2387a13c47e1ec3022dab46ffe7e79b7201f', '103.231.92.33', 1576134088, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343038383b),
('bbbc1e080bdf86a75bcdcb44ebdd4eedafd5f844', '103.231.92.33', 1576134088, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343038383b),
('e4a53e3c501b9cb0bb2d6df22d609d4411878567', '103.231.92.33', 1576134097, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343039373b),
('61e82b061d23f7d7027336e15eb9a70b54077a74', '103.231.92.33', 1576134098, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343039383b),
('7a2e78e55904ba60aed8d716c0d714297cfc283c', '103.231.92.33', 1576134099, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343039393b),
('efffbb664398a9e3d1cd654d08e56a641ce1bc3a', '103.231.92.33', 1576134101, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343130313b),
('05e3e8a25995ec36700dd0df8479cd3920814394', '103.231.92.33', 1576134103, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343130333b),
('a0d26640926cc972a11b14579fb4f01f943725d1', '103.231.92.33', 1576134111, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343131313b),
('2ca22008a4d6f6554182b4ea668f7829339660cd', '103.231.92.33', 1576134111, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343131313b),
('9baeaa7cee0a913947b966412fe509c1542d890c', '103.231.92.33', 1576134116, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343131363b),
('c457223757b7c407d43fbe71b13993b6746b0d15', '103.231.92.33', 1576134121, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343132313b),
('b624cf40fb0549b4d5c8bdfff53f17d8748948cc', '103.231.92.33', 1576134123, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343132333b),
('7432f5071a55cc4f32c5bada22227ba198800362', '103.231.92.33', 1576134131, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343133313b),
('67b8ba82298305fa32f8493ea40b6339c4c89bc7', '103.231.92.33', 1576134131, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343133313b),
('8dfdfe5ac840deb492cccc27a52f280ad04684d4', '103.231.92.33', 1576134131, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343133313b),
('ea23f25844738f8bf001032a9dfef3a4db06b10e', '103.231.92.33', 1576134131, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343133313b),
('445b246edf383791a293ee2bd24a0a6c504063ba', '103.231.92.33', 1576134131, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343133313b),
('21c982018e167391aad53ad28ff6e855214580c2', '103.231.92.33', 1576134132, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343133323b),
('bb0db4d5dde7e104068443109d09e82845207217', '103.231.92.33', 1576134143, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343134333b),
('01f7f3943a27cf7bda434a7e0887e8c738b6a72b', '103.231.92.33', 1576134148, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343134383b),
('7498cef1b850747c67dfdb4b97e0b3867306f8b8', '103.231.92.33', 1576136087, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363038373b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('998ad191b02727a2bcbd63879719fcdd4c4e0585', '103.231.92.33', 1576134213, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343231333b),
('e24447b81c9beaeb19d0f1d6368ea78c4fef9f4c', '103.231.92.33', 1576134214, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343231333b),
('190b452b3b935c6ae3cec0b9a06c6ff968076395', '103.231.92.33', 1576134216, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343231363b),
('e9b0d635fef773097c1b7d61b42cd81e6c6f79a6', '103.231.92.33', 1576134216, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343231363b),
('d56598b54dbfa985cbd005c5c12c7ba4f34e8924', '103.231.92.33', 1576134217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343231363b),
('f20afa5475e56984c6cdcbcf00e9003e12665f35', '103.231.92.33', 1576134217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343231373b),
('fee748c3757aad7adf79ed6fc2b9b76ba54730ff', '103.231.92.33', 1576134217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343231373b),
('580c7d8a45922fed53d2416db47b93d3a5cf84c1', '103.231.92.33', 1576134217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133343231373b),
('1ab7b65675842f39d253ab9af99ed74bb5e6f1af', '103.231.92.33', 1576135339, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353333393b),
('8370bf8e417faa62ce0748ed8d28cc8b8d0f3594', '103.231.92.33', 1576135340, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353334303b),
('d642107e8f7f8d3c7fc5a850aeb02e56d6cdb7fb', '103.231.92.33', 1576135341, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353334313b),
('c154fd69e50cebc93f18e157f469e3664d8fff22', '103.231.92.33', 1576135341, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353334313b),
('77212708e676d4017338671436f2d9166b5b8a82', '103.231.92.33', 1576135341, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353334313b),
('1ae40710bfb7feadab24750efe243329de35db61', '103.231.92.33', 1576135341, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353334313b),
('5bc62821d1c3d8ae11d331a5572ffa8af2409195', '103.231.92.33', 1576135342, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353334323b),
('ab38ffdc679b7e830eca471bf1dc56d0ebde4d25', '103.231.92.33', 1576135342, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353334323b),
('3e4d202795d3354a891c72268fca1e8ea93c0cbf', '103.231.92.33', 1576135356, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353335363b),
('3c2b5caf22a82052683cb92d130dd8098d82088c', '103.231.92.33', 1576135357, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353335373b),
('a17afdc4ad9560d99479135baf2d70d03cd2c12f', '103.231.92.33', 1576135361, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353336313b),
('0aff81cc1b61c53e1567b1d2f6b1c7892ccf2bb2', '103.231.92.33', 1576135361, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353336313b),
('212f8789e5dbb7230b33a43766d6b513c951def3', '103.231.92.33', 1576135362, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353336323b),
('9da90fe4cd2b778b8a9024fc5bba1b02d8c7025a', '103.231.92.33', 1576135362, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353336323b),
('7943534d67aa57ee99490940c2fa62a7d7b06513', '103.231.92.33', 1576135362, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353336323b),
('caa8c15147f72d98ebfef5441241512fa0400e77', '103.231.92.33', 1576135362, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353336323b),
('5aedebe6a85e5e32ae241cb64b431e2ee78674b0', '103.231.92.33', 1576135367, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353336373b),
('d83d72befc7369ed8b71a203d1ac4d5dc11be216', '103.231.92.33', 1576135368, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353336383b),
('8f070b5d4dcac6d4264ced972d90c3ef7679f3f5', '103.231.92.33', 1576135374, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337343b),
('21a83ffc55962f78dbbc0f5e2c9f2ceb2dcff933', '103.231.92.33', 1576135374, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337343b),
('291c0fb270b42322eb63cb0ebe6f2a2084b6a5ba', '103.231.92.33', 1576135374, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337343b),
('bcdcb65a9453031cec4204079de6e2a96156eba5', '103.231.92.33', 1576135375, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337353b),
('2a734ccbe133a117a3fb52e60eac6b27f11f3f0c', '103.231.92.33', 1576135375, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337353b),
('def181eace02ec7615d6fef4991e8a3814666c3a', '103.231.92.33', 1576135375, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337353b),
('d2746e881bd5cc26b1414ac05ca56d74eaf0f364', '103.231.92.33', 1576135376, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337363b),
('c13c392279096626e5ca98945f4b92bfb61e4782', '103.231.92.33', 1576135376, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337363b),
('0d9ad00ea41a8aa71693e379093bd4e8c659defe', '103.231.92.33', 1576135379, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337393b),
('89f91e858680ff845030d5992e6a3f62203c190e', '103.231.92.33', 1576135379, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353337393b),
('68b7c98cadfcbd6545ddc5cabbd21199525e8729', '103.231.92.33', 1576135380, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353338303b),
('6581a7ad6a5037f49108e6700961297cbe50a701', '103.231.92.33', 1576135384, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353338343b),
('cd7ae033d73743e2d0c4739d47899f3d42c1342d', '103.231.92.33', 1576135384, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353338343b),
('015ff7fe06d6c8d7064d268089287d900c3c273c', '103.231.92.33', 1576135384, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353338343b),
('342b9efa6fb9116d8ea8ad2372d41cfc04962ab9', '103.231.92.33', 1576135510, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531303b),
('e18dfca9eb46bd31c58a50a0fe0bcb0ee2c31d94', '103.231.92.33', 1576135511, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531313b),
('ead72c008334ca4518e83f9bf6c961eabb4892d5', '103.231.92.33', 1576135511, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531313b),
('5939f90017ed27b471c5375e9e4f5068907f8ab9', '103.231.92.33', 1576135511, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531313b),
('817092cc4d0cf1bd88fbe582979f68ac2025752a', '103.231.92.33', 1576135511, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531313b),
('1b20d2fd0df9dabe93f2b332d1c06bd3b21c37c8', '103.231.92.33', 1576135512, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531323b),
('1115a578c0b8172699bbd13a18c5feb1c4c32f04', '103.231.92.33', 1576135513, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531333b);
INSERT INTO `core_sessions` (`id`, `ip_address`, `timestamp`, `data`) VALUES
('7798435644429f0bc57ffd169e9402f4ace37f87', '103.231.92.33', 1576135513, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531333b),
('b443e15bb945a0aed6305a366bc0d4936a7ccbd7', '103.231.92.33', 1576135515, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531353b),
('147180c245f8861426af62181c30399d5b6cd182', '103.231.92.33', 1576135515, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531353b),
('5251a233e2c2bd35bb5fe0082ffec4ed8febcd43', '103.231.92.33', 1576135516, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531363b),
('9cb56d8e9f92efdbf7cd5842db550d4a85f5c639', '103.231.92.33', 1576135517, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531373b),
('3f09b0d425a2d6f07bad4e1b763dee0ea22b6aef', '103.231.92.33', 1576135517, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531373b),
('9311bff5d5e665e6f121b5bf175f6385d307db37', '103.231.92.33', 1576135517, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353531373b),
('097ce04258a29b0413f2f14a786021fc6c1c664c', '103.231.92.33', 1576135573, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537333b),
('6abd9d304f165f603fad4eeb5c20ebe396897420', '103.231.92.33', 1576135575, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537353b),
('6dd50119a8f81f4df8d24c6925b0b9663735ad9e', '103.231.92.33', 1576135575, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537353b),
('812f232e5bf693629b4c5394a8439928a22d1c99', '103.231.92.33', 1576135575, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537353b),
('3d3b382d54885eb45d816155f4a67cc6423651f3', '103.231.92.33', 1576135575, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537353b),
('b4c605226eec72c2d8f9e911d0b0a7b637c8e78a', '103.231.92.33', 1576135575, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537353b),
('4f7d201526a5e516b1a01cdf382511fbfa9a70ae', '103.231.92.33', 1576135576, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537363b),
('e20bb503e2ba363da4299357043db963fbfc9865', '103.231.92.33', 1576135576, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537363b),
('740f9c463d7eda6b7430b2e49b1994bb9010439a', '103.231.92.33', 1576135579, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537393b),
('3b6fddbdbbdab7bfb9afdcbb0518626e68655493', '103.231.92.33', 1576135579, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537393b),
('e6b2a22e39a7bcb95d70ed7270bdea1e4b5096c4', '103.231.92.33', 1576135579, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353537393b),
('ebe9094428803a884ef6bf1721cf82eb6fab1256', '103.231.92.33', 1576135580, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353538303b),
('b5b25da1059a052fe5a53f9302048f1502a8e7f3', '103.231.92.33', 1576135580, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353538303b),
('e1b9ff3ed12f86b8925a9632d7646acb4c335ec6', '103.231.92.33', 1576135581, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353538313b),
('db623e8c0319cdf4b3d41b7894b08973abfba420', '103.231.92.33', 1576135635, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353633353b),
('05b2d02394959bebbeccb83afa8509e0591004c3', '103.231.92.33', 1576135636, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353633363b),
('27fa27b4d94a56d94c64177ff5656b1dc40c9d7c', '103.231.92.33', 1576135636, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353633363b),
('a5cc86e4b11ba5eeafc8a056232cb835d7dd55e2', '103.231.92.33', 1576135636, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353633363b),
('7c1081077c6eca487bf69b3142927d62e82a15ed', '103.231.92.33', 1576135636, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353633363b),
('52197b58dcae6fa5b83e3b85b1635e31ca3c79db', '103.231.92.33', 1576135636, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353633363b),
('7babab6af8be9626e60a1be6ae8e391c262c0b00', '103.231.92.33', 1576135636, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353633363b),
('604dc423a754621aae26753b7c430be6dfd97b5e', '103.231.92.33', 1576135637, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353633373b),
('b1da31efa3bc46f688d1921cec44913083954339', '103.231.92.33', 1576135640, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353634303b),
('445d826824e372b7970e86c14ad4aa83441fb77b', '103.231.92.33', 1576135640, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353634303b),
('4172b83ac93004069716b46c7ead9dc612a4bd8d', '103.231.92.33', 1576135640, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353634303b),
('7cb3a8cd0d6a12e82ce8fccfcae7067f7a317fc6', '103.231.92.33', 1576135640, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353634303b),
('86e9035d533cb0b1057709582ac696e0d704a290', '103.231.92.33', 1576135641, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353634313b),
('776c9abfb01c92a169645df8f833dd1cb8a107b9', '103.231.92.33', 1576135641, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133353634313b),
('dfae68718c237900cfb66e43e27f2b8dc9cb05a9', '103.231.92.33', 1576136202, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363038373b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b737563636573737c733a33333a2243617220686173206265656e207375636365737366756c6c792075706461746564223b5f5f63695f766172737c613a313a7b733a373a2273756363657373223b733a333a226f6c64223b7d),
('f1a0929e3b9b7a173571f6282da85cbb5c03c55d', '103.231.92.33', 1576136103, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363130333b),
('a485103aa521fdfd875c5bdef182f27a8f5ab82d', '103.231.92.33', 1576136104, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363130343b),
('9bdbf622484369a2e545b84d25c4d67ffac47c4b', '103.231.92.33', 1576136104, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363130343b),
('2ec591fbf7d2f641a76f557f56ba8d83b03d63ca', '103.231.92.33', 1576136104, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363130343b),
('ad62624dd3ff2a5f8084539f4c714e0e31c49b7e', '103.231.92.33', 1576136104, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363130343b),
('4d26ed37c83ebc11ec88f23caca1e4bc387b245b', '103.231.92.33', 1576136104, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363130343b),
('f908c14a5e0a6f0aaf0c4dc407a86f94190be648', '103.231.92.33', 1576136105, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363130353b),
('b0d5ec198b212c417f15bd0858091b5662968539', '103.231.92.33', 1576136105, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363130353b),
('a55741803e6d9f219a2ad76daf327269fa570c69', '103.231.92.33', 1576136127, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363132373b),
('5f987cadd214941e62d8ea92f47535db77dd26e8', '103.231.92.33', 1576136128, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363132383b),
('af7534c7d63d65da21bc84d13af4c8c6f3faf18f', '103.231.92.33', 1576136128, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363132383b),
('7ad49a7342ea3ff91e6b265e45ebf79b03873fa7', '103.231.92.33', 1576136132, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363133323b),
('ae2a1eecd668162e18372f807bc9723934faf803', '103.231.92.33', 1576136132, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363133323b),
('86f707b30e794cff4b9924dbc81249f1fad4b050', '103.231.92.33', 1576136132, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363133323b),
('753a2c184c5862fdc9b37008fb1d9598f4fb5fcd', '103.231.92.33', 1576136148, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363134383b),
('67e66e059983beccaccaf9f21f94f0eec44405e2', '103.231.92.33', 1576136149, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363134393b),
('68160af26c98dedc33fa0cd63431a1006e9c8923', '103.231.92.33', 1576136150, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363135303b),
('b3bf92482e713e90a7fd75b2576f06bb45dbd05e', '103.231.92.33', 1576136159, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363135393b),
('129f498ac4c2be80232d32b6fcdef995824a4e58', '103.231.92.33', 1576136163, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363136333b),
('05c55c76ff7a7cecb9e6d9665a14f111f2862905', '103.231.92.33', 1576136169, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363136393b),
('c93a0771da8968b3f94b96242206bdace665a362', '103.231.92.33', 1576136175, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363137353b),
('dc11d1469e99a798f32a203001ddd4ee9fb19f5d', '103.231.92.33', 1576136186, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363138363b),
('bf4f656cf75350ab76909bd2a6454521fa3ab08f', '103.231.92.33', 1576136187, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363138363b),
('9a7a781b41845fdc597f427a5a862b31c46a4ba4', '103.231.92.33', 1576136187, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363138373b),
('5bde004eb0347c01a90fd3d57cd63aa600c26352', '103.231.92.33', 1576136191, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363139313b),
('f7085d247b7fd02c83793c964f28168715d368fa', '103.231.92.33', 1576136191, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363139313b),
('ee80daff21c2a643fce8b614d321f8f0a3595263', '103.231.92.33', 1576136191, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363139313b),
('2a296f596e8953306792229a85871d1002d40809', '103.231.92.33', 1576136206, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363230363b),
('076af8fb87c9ee670093d4d5bea2aa0b192970f3', '103.231.92.33', 1576136207, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363230373b),
('37e3a29466305a1c0f25218cda25de566ab58cab', '103.231.92.33', 1576136207, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363230373b),
('3f738e9c19221fab418281ff1dc7a625859b6424', '103.231.92.33', 1576136212, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231323b),
('d0cdfb3cbe5765fb6c84488252edd9fd6dece88b', '103.231.92.33', 1576136214, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231343b),
('3fffc29581ba3c8d436bced673f91543abe9b538', '103.231.92.33', 1576136215, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231353b),
('80744ede0e9737d9cf684634a56e18db878f4c98', '103.231.92.33', 1576136216, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231353b),
('cdaa7e62f05320658ef5babcac3c674b29dee9b8', '103.231.92.33', 1576136216, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231353b),
('fd2c710b0b266458e2038fa5717bdaf7bba7570e', '103.231.92.33', 1576136216, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231353b),
('6943cba1ddd826af4f9b91e05ff1a6879eabce7a', '103.231.92.33', 1576136217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231363b),
('f664421d7bc4d1863980ea120cb453d85834aeda', '103.231.92.33', 1576136217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231363b),
('22be992fcb2ad985a22800d37f588436b17b5b47', '103.231.92.33', 1576136217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231373b),
('d34666330324536efef1e429c3fb26f242eb3bc5', '103.231.92.33', 1576136218, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231373b),
('3bf5ff37cc11618c62d02a85f5145f44d1afaed1', '103.231.92.33', 1576136219, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363231393b),
('335b5295a0b594beec0146287e021efa9151f051', '103.231.92.33', 1576136220, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363232303b),
('989b7726244401a1827ead5bd20f9af5ffad3c1f', '103.231.92.33', 1576136223, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363232333b),
('c496f435245cff5ccf12998481583a08084adae3', '103.231.92.33', 1576136223, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363232333b),
('80adde2b8c3a1b3b542a4bbd5ef31f8b8e35ea6e', '103.231.92.33', 1576136223, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363232333b),
('77a8ff3b9527f4e84c4c09563fd877b90fd7bc3e', '103.231.92.33', 1576136224, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363232343b),
('c7fdeff64ebe5278f796a165d3aaccd510baa9bf', '103.231.92.33', 1576136224, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363232343b),
('b9c33396f3ddaa84a2e12ec0d6175e17a8717a51', '103.231.92.33', 1576136224, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363232343b),
('c80f65aa6eaa2ce20f7813862ca3a6dce0e5ca7c', '103.231.92.33', 1576136233, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363233333b),
('e999c49c1ff06a28c375c5bde1ca0eaac3221ec8', '103.231.92.33', 1576136233, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363233333b),
('10b70cde786ff66c495645fd759b358bcdeed4b3', '103.231.92.33', 1576136238, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363233383b),
('48132f218d23c859eeb28ebf388c6c74d824e308', '103.231.92.33', 1576136245, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363234353b),
('d3178deb543bac717fec8825fbe436545a6bf0f9', '103.231.92.33', 1576136246, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363234363b),
('0f1ca2e7a78f462684a5164c47a56603c1cab55f', '103.231.92.33', 1576136247, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363234373b),
('0286f4d22477d903169e2d0e8a4692ce87e3c673', '103.231.92.33', 1576136247, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363234373b),
('a8502a79022144999736b62aec8be8ca08232f4b', '103.231.92.33', 1576136247, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363234373b),
('0eab6c73679c8b4f81de3b7a3a99abd4a0af4caf', '103.231.92.33', 1576136247, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363234373b),
('9d8612a31ff40c4ae9cec148f70bb02630cd4276', '103.231.92.33', 1576136257, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363235373b),
('e81aa46d41f8b015ec0d92fca774ab2d6f4c5caf', '103.231.92.33', 1576136258, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363235383b),
('23262f7df5430885f0fbec78a440eac726346e8d', '103.231.92.33', 1576136258, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363235383b),
('97c3534cac5c2e64e79b0dccf3fbd738df7c5736', '103.231.92.33', 1576136259, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363235393b),
('81b10771407ee810ea847e3914a68b74731a4e4d', '103.231.92.33', 1576136259, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363235393b),
('c9269ebf270c46ac5ddca9e3ba32139a5cfd2956', '103.231.92.33', 1576136260, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363236303b),
('a5c5d933ec2933f020bffe9bce11aba5082512c0', '103.231.92.33', 1576136262, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363236323b),
('301504d948905ca6011d54acde623d37c7148941', '103.231.92.33', 1576136262, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363236323b),
('1fa11e1370c45fc864e103b615d4e553b350fe21', '103.231.92.33', 1576136262, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363236323b),
('f601a77c03119f8da6c849338d958a63d5c3a9bb', '103.231.92.33', 1576136266, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363236363b),
('f5c6d01799f431a1d2536406cebcf6e0605a438a', '103.231.92.33', 1576136267, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363236373b),
('adace278484594c39f5b37458822cd058249bf2e', '103.231.92.33', 1576136339, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363333393b),
('47124a33664153510eb10745de607a484d10d620', '103.231.92.33', 1576136339, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363333393b),
('a894cf0ed1fc653d372711b15e4f914551290f08', '103.231.92.33', 1576136340, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334303b),
('db391cbf6149eaa800d30c91afbef45e4f7311c3', '103.231.92.33', 1576136344, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334343b),
('f0554c4c8a97bf1447801d13427ebc6cf6e870b4', '103.231.92.33', 1576136344, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334343b),
('4123361e8a41e84756dd7f6ff4f7e3576c2c1f0d', '103.231.92.33', 1576136344, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334343b),
('d83e41682eb2d1900ec8fddc8ad362dbdce1bc8a', '103.231.92.33', 1576136346, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334363b),
('232662aaa1f1ea78dfd5c95ff56af67f1304ae54', '103.231.92.33', 1576136346, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334363b),
('28e8646634fffebab798e7ea984d7f26ac67b2d6', '103.231.92.33', 1576136346, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334363b),
('16943956c7ceb4a6b878c48d21b54a2064df564f', '103.231.92.33', 1576136347, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334373b),
('5f6bc7d948c1208a843ae672713f6944fd93d048', '103.231.92.33', 1576136347, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334373b),
('2323c047fcab13dc2b65768144d711b99d729657', '103.231.92.33', 1576136347, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363334373b),
('f0c1eeff983edce62f0fc5590daa63c7010504a7', '103.231.92.33', 1576136352, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363335313b),
('101c39e10c02bf9343bae8ce624b3d0bd765d748', '103.231.92.33', 1576136352, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363335323b),
('fbd5c8bb76706008d861dacf6dad1930e707707a', '103.231.92.33', 1576136367, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363336373b),
('811388a487c9bd207ab026ea36d4a926676721ce', '103.231.92.33', 1576136367, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363336373b),
('99418c80bad1cc2c3d4adf95ffb4cf8274345cdb', '103.231.92.33', 1576136369, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363336393b),
('79c5594e69d159eda5fcaebc51c7949ae260db6f', '103.231.92.33', 1576136370, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337303b),
('1d5bd46cb595510226b8baa8da11dadca397ff5d', '103.231.92.33', 1576136370, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337303b),
('570e0dabe9c5a12eee45db20cdeb1e572a5a050d', '103.231.92.33', 1576136370, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337303b),
('0ae93fb60f6297df4fc439ae6002d0ea88298af1', '103.231.92.33', 1576136371, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337313b),
('6096211beffcb4a76ac4a132ade0c001cb94bc12', '103.231.92.33', 1576136376, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337363b),
('a5b4816a385029f595632e185d1441c1aab7d1c3', '103.231.92.33', 1576136376, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337363b),
('6c1fa002deda109ad6a451c6a7095b59cf11e11f', '103.231.92.33', 1576136376, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337363b),
('0bf1ddc12bd308970048d7df6964876a10533106', '103.231.92.33', 1576136377, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337373b),
('7be83d38e84f9c4fedf039295c4d7a41d9ebc75b', '103.231.92.33', 1576136377, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363337373b),
('303c164743dfb35ca60907f108f48cc04feb622f', '103.231.92.33', 1576136381, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363133363338313b),
('b6869b7c5ef5f1e4cdea51167ad4379375adf263', '103.231.92.225', 1576146119, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363134353936373b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b),
('f39ea07da28c780119ad5709f34850093d2fccdd', '103.231.92.225', 1576147426, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363134373432363b),
('091ed6e46ed5481d68d5d42d6808c50a0af292f9', '103.231.92.62', 1576210155, 0x5f5f63695f6c6173745f726567656e65726174657c693a313537363231303132333b757365725f69647c733a33323a226334636134323338613062393233383230646363353039613666373538343962223b726f6c655f69647c733a313a2231223b69735f7379735f61646d696e7c733a313a2231223b737563636573737c733a33373a2256657273696f6e20686173206265656e207375636365737366756c6c792075706461746564223b5f5f63695f766172737c613a313a7b733a373a2273756363657373223b733a333a226f6c64223b7d);

-- --------------------------------------------------------

--
-- Table structure for table `core_users`
--

CREATE TABLE `core_users` (
  `user_id` varchar(255) NOT NULL,
  `user_is_sys_admin` int(11) NOT NULL DEFAULT '0',
  `facebook_id` varchar(255) NOT NULL,
  `google_id` varchar(255) NOT NULL,
  `phone_id` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `user_phone` varchar(255) NOT NULL,
  `user_address` text NOT NULL,
  `city` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_about_me` text NOT NULL,
  `user_cover_photo` varchar(255) NOT NULL,
  `user_profile_photo` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL DEFAULT '4',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1=active, 0=inactive',
  `is_banned` tinyint(1) NOT NULL DEFAULT '0',
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `device_token` text NOT NULL,
  `code` varchar(10) NOT NULL,
  `overall_rating` float NOT NULL DEFAULT '0',
  `whatsapp` varchar(255) NOT NULL,
  `messenger` varchar(255) NOT NULL,
  `follower_count` int(11) NOT NULL,
  `following_count` int(11) NOT NULL,
  `email_verify` tinyint(1) NOT NULL,
  `facebook_verify` tinyint(1) NOT NULL,
  `google_verify` tinyint(1) NOT NULL,
  `phone_verify` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_users`
--

INSERT INTO `core_users` (`user_id`, `user_is_sys_admin`, `facebook_id`, `google_id`, `phone_id`, `user_name`, `user_email`, `user_phone`, `user_address`, `city`, `user_password`, `user_about_me`, `user_cover_photo`, `user_profile_photo`, `role_id`, `status`, `is_banned`, `added_date`, `device_token`, `code`, `overall_rating`, `whatsapp`, `messenger`, `follower_count`, `following_count`, `email_verify`, `facebook_verify`, `google_verify`, `phone_verify`) VALUES
('c4ca4238a0b923820dcc509a6f75849b', 1, '', '', '', 'PS Ad Motor Admin', 'admin@ps.com', '09785578337', 'Thitsar Road, South Okala, Yangon', 'Yangon', '21232f297a57a5a743894a0e4a801fc3', 'this is about me', '', 'people_icon.jpg', '1', 1, 0, '2017-10-25 00:19:55', 'test001', '', 5, '', '', 0, 0, 0, 0, 0, 0),
('usr8dad6e9376480737a9a725e5dab8c402', 0, '', '', '', 'Brown', 'brown@gmail.com', '097894545', 'No 11, Main Road, Pacitan', 'Pacitan', '827ccb0eea8a706c4c34a16891f84e7b', 'it is about me', '', 'man_220191126061056.png', '4', 1, 0, '2019-11-26 05:03:16', '', '', 0, '', '', 0, 0, 1, 0, 0, 0),
('usrcb9099221620a3553a5f0d80b481c063', 0, '', 'sRVvdTJuWQMKK0msfXrj3iCHoHf1', '', 'PS Dev', 'teamps.dev@gmail.com', '', '', '', ' ', '', '', 'b9305262ecbdf632f54f219d8330ff5e.jpg', '4', 1, 0, '2019-11-29 10:13:03', 'fCIgtXQOF0Y:APA91bEMj8j7T1wa-4OpDPi4NOYV0Oqqoauvi5wTNsXCJYn8tzVDzlA5Zk7SvBKeue8FdQbr8WyGMD34ymz-sJbL2cXgYRtpM2-TFYLM-Hh012D6IYuJ56mFGkJ1DF9txpo62USLmyDg', ' ', 0, '', '', 0, 0, 0, 0, 1, 0),
('usre16cb198d292bb9fdde6f2689a00f258', 0, '', '', '', 'Frank', 'frank@gmail.com', '095856415', 'No 70, Railway Road, Pacitan', 'Pacitan', '827ccb0eea8a706c4c34a16891f84e7b', 'it is about me', '', 'man_520191126062519.png', '4', 1, 0, '2019-11-26 05:21:53', '', '', 0, '', '', 0, 0, 1, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bs_accept_offer`
--
ALTER TABLE `bs_accept_offer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_apis`
--
ALTER TABLE `bs_apis`
  ADD PRIMARY KEY (`api_id`);

--
-- Indexes for table `bs_api_keys`
--
ALTER TABLE `bs_api_keys`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_app`
--
ALTER TABLE `bs_app`
  ADD PRIMARY KEY (`app_id`);

--
-- Indexes for table `bs_app_settings`
--
ALTER TABLE `bs_app_settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_categories_request`
--
ALTER TABLE `bs_categories_request`
  ADD PRIMARY KEY (`request_cat_id`);

--
-- Indexes for table `bs_chat_history`
--
ALTER TABLE `bs_chat_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_contact`
--
ALTER TABLE `bs_contact`
  ADD PRIMARY KEY (`contact_id`);

--
-- Indexes for table `bs_delete_history`
--
ALTER TABLE `bs_delete_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_favourite`
--
ALTER TABLE `bs_favourite`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_feeds`
--
ALTER TABLE `bs_feeds`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_follows`
--
ALTER TABLE `bs_follows`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_home_banners`
--
ALTER TABLE `bs_home_banners`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items`
--
ALTER TABLE `bs_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_build_types`
--
ALTER TABLE `bs_items_build_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_colors`
--
ALTER TABLE `bs_items_colors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_currency`
--
ALTER TABLE `bs_items_currency`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_fuel_types`
--
ALTER TABLE `bs_items_fuel_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_location`
--
ALTER TABLE `bs_items_location`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_price`
--
ALTER TABLE `bs_items_price`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_report`
--
ALTER TABLE `bs_items_report`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_seller_types`
--
ALTER TABLE `bs_items_seller_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_transmissions`
--
ALTER TABLE `bs_items_transmissions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_items_types`
--
ALTER TABLE `bs_items_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_item_conditions`
--
ALTER TABLE `bs_item_conditions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_manufacturers`
--
ALTER TABLE `bs_manufacturers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_models`
--
ALTER TABLE `bs_models`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_order_by`
--
ALTER TABLE `bs_order_by`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_push_notification_messages`
--
ALTER TABLE `bs_push_notification_messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_push_notification_tokens`
--
ALTER TABLE `bs_push_notification_tokens`
  ADD PRIMARY KEY (`push_noti_token_id`);

--
-- Indexes for table `bs_push_notification_users`
--
ALTER TABLE `bs_push_notification_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_ratings`
--
ALTER TABLE `bs_ratings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bs_touches`
--
ALTER TABLE `bs_touches`
  ADD PRIMARY KEY (`touch_id`);

--
-- Indexes for table `bs_version`
--
ALTER TABLE `bs_version`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `core_about`
--
ALTER TABLE `core_about`
  ADD PRIMARY KEY (`about_id`);

--
-- Indexes for table `core_images`
--
ALTER TABLE `core_images`
  ADD PRIMARY KEY (`img_id`);

--
-- Indexes for table `core_menu_groups`
--
ALTER TABLE `core_menu_groups`
  ADD PRIMARY KEY (`group_id`);

--
-- Indexes for table `core_modules`
--
ALTER TABLE `core_modules`
  ADD PRIMARY KEY (`module_id`);

--
-- Indexes for table `core_permissions`
--
ALTER TABLE `core_permissions`
  ADD PRIMARY KEY (`user_id`,`module_id`);

--
-- Indexes for table `core_reset_codes`
--
ALTER TABLE `core_reset_codes`
  ADD PRIMARY KEY (`code_id`);

--
-- Indexes for table `core_roles`
--
ALTER TABLE `core_roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `core_role_access`
--
ALTER TABLE `core_role_access`
  ADD PRIMARY KEY (`role_id`,`action_id`);

--
-- Indexes for table `core_users`
--
ALTER TABLE `core_users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `core_modules`
--
ALTER TABLE `core_modules`
  MODIFY `module_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
