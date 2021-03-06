USE [master]
GO
/****** Object:  Database [UserManagement]    Script Date: 7/14/2021 10:29:18 PM ******/
CREATE DATABASE [UserManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'UserManagement', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\UserManagement.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'UserManagement_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\UserManagement_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [UserManagement] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [UserManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [UserManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [UserManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [UserManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [UserManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [UserManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [UserManagement] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [UserManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [UserManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [UserManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [UserManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [UserManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [UserManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [UserManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [UserManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [UserManagement] SET  DISABLE_BROKER 
GO
ALTER DATABASE [UserManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [UserManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [UserManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [UserManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [UserManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [UserManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [UserManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [UserManagement] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [UserManagement] SET  MULTI_USER 
GO
ALTER DATABASE [UserManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [UserManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [UserManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [UserManagement] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [UserManagement] SET DELAYED_DURABILITY = DISABLED 
GO
USE [UserManagement]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 7/14/2021 10:29:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[roleId] [varchar](10) NOT NULL,
	[roleName] [varchar](100) NULL,
	[roleStatus] [varchar](100) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[User]    Script Date: 7/14/2021 10:29:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[User](
	[userId] [varchar](100) NOT NULL,
	[userName] [nvarchar](150) NULL,
	[userPassword] [varchar](100) NULL,
	[userImage] [varchar](max) NULL,
	[userEmail] [varchar](100) NULL,
	[roleId] [varchar](10) NULL,
	[userPhone] [varchar](20) NULL,
	[userCreateDate] [datetime] NULL,
	[userStatus] [varchar](50) NULL,
	[promotionStatus] [varchar](50) NULL,
	[promotionRank] [float] NULL,
	[promotionDate] [datetime] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Role] ([roleId], [roleName], [roleStatus]) VALUES (N'EM', N'Employee', N'active')
INSERT [dbo].[Role] ([roleId], [roleName], [roleStatus]) VALUES (N'MA', N'Manager', N'active')
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'anhthu123', N'Anh Thu', N'49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c', N'assets/img/pngtree-flat-avatar-sailor-cartoon-user-png-image_4492900.jpg', N'anhthu@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-09 12:40:59.330' AS DateTime), N'inactive', N'active', 9, CAST(N'2021-06-12 13:18:54.557' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'anhthu456', N'123', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-flat-avatar-sailor-cartoon-user-png-image_4492900.jpg', N'anhthu@gmail.com', N'EM', N'0352349283', CAST(N'2021-06-05 00:00:00.000' AS DateTime), N'active', N'active', 7, CAST(N'2021-06-22 07:59:17.180' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'congphuong', N'Cong Phuong', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-flat-avatar-sailor-cartoon-user-png-image_4492900.jpg', N'congphuong@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-19 14:49:30.913' AS DateTime), N'active', N'inactive', 0, NULL)
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai12345', N'Hoang Thai Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-user-cartoon-avatar-pattern-png-image_4492878.jpg', N'hoangthai22tv@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-13 21:13:30.010' AS DateTime), N'active', N'active', 5, CAST(N'2021-06-18 15:40:43.757' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai123456', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-black-round-pattern-flat-avatar-user-png-image_4492905.jpg', N'hoangthai22tv@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-22 07:58:36.377' AS DateTime), N'active', N'inactive', 0, NULL)
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai22', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-flat-avatar-sailor-cartoon-user-png-image_4492900.jpg', N'hoangthai2222tv@gmail.com', N'MA', N'0353270383', CAST(N'2021-06-04 00:00:00.000' AS DateTime), N'active', N'inactive', 0, NULL)
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai222', N'Hoang Thai', N'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', N'assets/img/pngtree-user-cartoon-avatar-pattern-png-image_4492878.jpg', N'hoangthai22tv@gmail.com', N'MA', N'0353270383', CAST(N'2021-06-20 20:38:53.273' AS DateTime), N'active', N'inactive', 0, NULL)
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai22222', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-zephyr-flat-user-pattern-png-image_4492898.jpg', N'myngan@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-08 21:36:49.970' AS DateTime), N'inactive', N'active', 1, CAST(N'2021-06-11 15:16:55.740' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai222222', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-flat-avatar-sailor-cartoon-user-png-image_4492900.jpg', N'hoangthai22tv@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-21 22:50:53.157' AS DateTime), N'inactive', N'inactive', 0, NULL)
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai2233', N'Hoang Thai', N'49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c', N'assets/img/pngtree-black-round-pattern-flat-avatar-user-png-image_4492905.jpg', N'hoangthai22tv@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-08 23:13:52.627' AS DateTime), N'active', N'active', 9.0100002288818359, CAST(N'2021-06-13 21:19:39.660' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai23', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-user-cartoon-avatar-pattern-png-image_4492878.jpg', N'hoangthai22tv@gmail.com', N'MA', N'0353270383', CAST(N'2021-06-20 21:02:51.807' AS DateTime), N'active', N'inactive', 5, CAST(N'2021-06-20 21:14:49.630' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'hoangthai44444', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-user-cartoon-avatar-pattern-flat-avatar-png-image_4492883.jpg', N'hoangthai22tv@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-07 15:20:46.183' AS DateTime), N'active', N'active', 5, CAST(N'2021-06-20 21:14:49.627' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'myngan', N'My Ngan', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-zephyr-flat-user-pattern-png-image_4492898.jpg', N'myngan@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-05 16:39:16.320' AS DateTime), N'active', N'inactive', 0, NULL)
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'myngan4444', N'My Ngan Ha', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-user-cartoon-girl-avatar-png-image_4492903.jpg', N'myngan@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-08 21:42:46.180' AS DateTime), N'active', N'inactive', 5, CAST(N'2021-06-20 21:14:49.630' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'ngocphuc', N'Ngoc phuc', N'49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c', N'assets/img/pngtree-user-cartoon-girl-avatar-png-image_4492903.jpg', N'ngocphuc@gmail.com', N'EM', N'0987654321', CAST(N'2021-06-05 17:01:57.143' AS DateTime), N'active', N'active', 5.0100002288818359, CAST(N'2021-06-18 15:41:03.153' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'ngocphuc123', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/user-medium.png', N'hoangthai22tv@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-05 17:08:44.613' AS DateTime), N'inactive', N'active', 5, CAST(N'2021-06-10 16:40:56.623' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'thainguyen', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-flat-avatar-sailor-cartoon-user-png-image_4492900.jpg', N'hoangthai22tv@gmail.com', N'EM', N'0353270383', CAST(N'2021-06-08 21:41:46.677' AS DateTime), N'inactive', N'inactive', 2, CAST(N'2021-06-10 01:21:50.847' AS DateTime))
INSERT [dbo].[User] ([userId], [userName], [userPassword], [userImage], [userEmail], [roleId], [userPhone], [userCreateDate], [userStatus], [promotionStatus], [promotionRank], [promotionDate]) VALUES (N'thainguyen22', N'Hoang Thai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'assets/img/pngtree-flat-avatar-sailor-cartoon-user-png-image_4492900.jpg', N'hoangthai22tv@gmail.com', N'MA', N'0353270383', CAST(N'2021-06-08 21:38:37.103' AS DateTime), N'active', N'active', 5, CAST(N'2021-06-21 22:51:40.523' AS DateTime))
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[Role] ([roleId])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Role]
GO
USE [master]
GO
ALTER DATABASE [UserManagement] SET  READ_WRITE 
GO
