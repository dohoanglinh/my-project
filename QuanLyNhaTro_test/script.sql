USE [master]
GO
/****** Object:  Database [QuanLiNhaTro]    Script Date: 05/15/2019 20:30:40 ******/
CREATE DATABASE QuanLiNhaTro 
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLiNhaTro].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLiNhaTro] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET ANSI_NULLS OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET ANSI_PADDING OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET ARITHABORT OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET AUTO_CLOSE ON
GO
ALTER DATABASE [QuanLiNhaTro] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [QuanLiNhaTro] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [QuanLiNhaTro] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [QuanLiNhaTro] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET  ENABLE_BROKER
GO
ALTER DATABASE [QuanLiNhaTro] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [QuanLiNhaTro] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [QuanLiNhaTro] SET  READ_WRITE
GO
ALTER DATABASE [QuanLiNhaTro] SET RECOVERY SIMPLE
GO
ALTER DATABASE [QuanLiNhaTro] SET  MULTI_USER
GO
ALTER DATABASE [QuanLiNhaTro] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [QuanLiNhaTro] SET DB_CHAINING OFF
GO
USE [QuanLiNhaTro]
GO
/****** Object:  Table [dbo].[NguoiQuanLy]    Script Date: 05/15/2019 20:30:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NguoiQuanLy](
	[MaNQL] [char](5) NOT NULL,
	[MatKhau] [varchar](50) NULL,
	[TenNQL] [nvarchar](50) NULL,
	[VaiTro] [bit] NULL,
 CONSTRAINT [PK_NguoiQuanLy] PRIMARY KEY CLUSTERED 
(
	[MaNQL] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NguoiDung]    Script Date: 05/15/2019 20:30:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NguoiDung](
	[MaND] [char](5) NOT NULL,
	[TenND] [nvarchar](50) NULL,
	[GioiTinh] [bit] NULL,
	[Tuoi] [int] NULL,
	[DiaChi] [nvarchar](225) NULL,
	[DienThoai] [varchar](10) NULL,
	[Email] [nvarchar](225) NULL,
	[CMND] [nvarchar](9) NULL,
	[Hinh] [nvarchar](225) NULL,
 CONSTRAINT [PK__NguoiDun__2725D72403317E3D] PRIMARY KEY CLUSTERED 
(
	[MaND] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiNha]    Script Date: 05/15/2019 20:30:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LoaiNha](
	[MaLoaiNha] [char](5) NOT NULL,
	[LoaiNhaTro] [nvarchar](50) NULL,
	[GhiChu] [nvarchar](225) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoaiNha] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DanhGia]    Script Date: 05/15/2019 20:30:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DanhGia](
	[STT] [int] IDENTITY(1,1) NOT NULL,
	[MaND] [char](5) NULL,
	[MaPT] [char](5) NULL,
	[YeuThich] [bit] NULL,
	[DanhGia] [nvarchar](225) NULL,
PRIMARY KEY CLUSTERED 
(
	[STT] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PhongTro]    Script Date: 05/15/2019 20:30:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PhongTro](
	[MaPT] [char](5) NOT NULL,
	[MaLoaiNha] [char](5) NULL,
	[DienTich] [float] NULL,
	[GiaPhong] [money] NULL,
	[DiaChi] [nvarchar](225) NULL,
	[MoTa] [nvarchar](225) NULL,
	[NgayDangTin] [date] NULL,
	[LienHe] [char](5) NULL,
 CONSTRAINT [PK__PhongTro__2725E7F60AD2A005] PRIMARY KEY CLUSTERED 
(
	[MaPT] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  StoredProcedure [dbo].[tk_DanhGia]    Script Date: 05/15/2019 20:30:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[tk_DanhGia]
as begin
	select MaPT, COUNT(YeuThich) as LuotLike from DanhGia 
	where YeuThich=1
	Group by MaPT
end
GO
/****** Object:  StoredProcedure [dbo].[TimKiem]    Script Date: 05/15/2019 20:30:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[TimKiem]
@maLNT varchar(5)='%',
@giaMin money=0,
@giaMax money=100000000,  
@dtMin float=0, 
@dtMax float=1000000
as 
begin
	select * from PhongTro where (MaLoaiNha like @maLNT) and (GiaPhong between @giaMin and @giaMax) 
			and (DienTich between @dtMin and @dtMax) 
end
GO
/****** Object:  StoredProcedure [dbo].[CountLike]    Script Date: 05/15/2019 20:30:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[CountLike]( @MaPT char(5) )
as begin
	Select Count(YeuThich) SL from DanhGia where MaPT like @MaPT and YeuThich=1
end
GO
/****** Object:  StoredProcedure [dbo].[CountDislike]    Script Date: 05/15/2019 20:30:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[CountDislike] (@MaPT char(5) )
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT Count(YeuThich) from DanhGia where MaPT=@MaPT and YeuThich=0
END
GO
/****** Object:  Table [dbo].[HopDong]    Script Date: 05/15/2019 20:30:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HopDong](
	[MaHD] [char](5) NOT NULL,
	[MaND] [char](5) NOT NULL,
	[MaPT] [char](5) NOT NULL,
	[NgayTao] [date] NOT NULL,
	[NgayBatDau] [date] NOT NULL,
	[NgayHetHan] [date] NOT NULL,
	[TienCuoc] [money] NOT NULL,
	[TienTraTheoThang] [money] NOT NULL,
	[TraTruocCon] [money] NOT NULL,
	[TongTien] [money] NOT NULL,
	[GhiChu] [nvarchar](225) NULL,
 CONSTRAINT [PK__HopDong__2725A6E00F975522] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  StoredProcedure [dbo].[tk_NguoiThue]    Script Date: 05/15/2019 20:30:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[tk_NguoiThue](@Nam int)
as begin
if(@Nam >0)
	begin
		select YEAR(NgayBatDau) as Nam ,COUNT(MaND) as SoLuong from HopDong
		where YEAR(NgayBatDau)=@Nam
		group by YEAR(NgayBatDau)
		order by YEAR(NgayBatDau)
	end
else
	begin
		select YEAR(NgayBatDau) as Nam, COUNT(MaND) as SoLuong from HopDong
		group by YEAR(NgayBatDau)
		order by YEAR(NgayBatDau)
	end
end
GO
/****** Object:  StoredProcedure [dbo].[tk_DoanhThu]    Script Date: 05/15/2019 20:30:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[tk_DoanhThu](@Nam int)
as 
begin
	select @Nam, sum(TienTraTheoThang) from HopDong
	where @Nam between YEAR(NgayBatDau) and YEAR(NgayHetHan)
end
GO
/****** Object:  ForeignKey [NT-DG]    Script Date: 05/15/2019 20:30:41 ******/
ALTER TABLE [dbo].[DanhGia]  WITH CHECK ADD  CONSTRAINT [NT-DG] FOREIGN KEY([MaND])
REFERENCES [dbo].[NguoiDung] ([MaND])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DanhGia] CHECK CONSTRAINT [NT-DG]
GO
/****** Object:  ForeignKey [FK__PhongTro__MaLoai__0CBAE877]    Script Date: 05/15/2019 20:30:41 ******/
ALTER TABLE [dbo].[PhongTro]  WITH CHECK ADD  CONSTRAINT [FK__PhongTro__MaLoai__0CBAE877] FOREIGN KEY([MaLoaiNha])
REFERENCES [dbo].[LoaiNha] ([MaLoaiNha])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PhongTro] CHECK CONSTRAINT [FK__PhongTro__MaLoai__0CBAE877]
GO
/****** Object:  ForeignKey [FK_PhongTro_NguoiQuanLy]    Script Date: 05/15/2019 20:30:41 ******/
ALTER TABLE [dbo].[PhongTro]  WITH CHECK ADD  CONSTRAINT [FK_PhongTro_NguoiQuanLy] FOREIGN KEY([LienHe])
REFERENCES [dbo].[NguoiQuanLy] ([MaNQL])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PhongTro] CHECK CONSTRAINT [FK_PhongTro_NguoiQuanLy]
GO
/****** Object:  ForeignKey [NT-HD]    Script Date: 05/15/2019 20:30:52 ******/
ALTER TABLE [dbo].[HopDong]  WITH CHECK ADD  CONSTRAINT [NT-HD] FOREIGN KEY([MaND])
REFERENCES [dbo].[NguoiDung] ([MaND])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HopDong] CHECK CONSTRAINT [NT-HD]
GO
/****** Object:  ForeignKey [PT-HD]    Script Date: 05/15/2019 20:30:52 ******/
ALTER TABLE [dbo].[HopDong]  WITH CHECK ADD  CONSTRAINT [PT-HD] FOREIGN KEY([MaPT])
REFERENCES [dbo].[PhongTro] ([MaPT])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HopDong] CHECK CONSTRAINT [PT-HD]
GO
