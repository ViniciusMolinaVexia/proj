USE [RMOnline]
GO
/****** Object:  Trigger [dbo].[insertNumeroRM]    Script Date: 28/04/2022 17:51:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE trigger [dbo].[insertNumeroRM]
ON [dbo].[TB_RM]
AFTER INSERT
AS
BEGIN
declare
@id int,
@ultimoNumeroRm int = 0,
@numeroRm varchar(250)
SET NOCOUNT ON;
select @id=RM_ID, @numeroRm=NUMERO_RM from INSERTED
IF (select TIPO_RM from INSERTED) LIKE 'MAT'
 	begin
      select @ultimoNumeroRm = COALESCE(max(convert(int, NUMERO_RM)),0)+1 from TB_RM where ISNUMERIC(NUMERO_RM)=1
    end
IF @numeroRm IS NULL
	begin
      update TB_RM set NUMERO_RM = @ultimoNumeroRm where RM_ID=@id
	end
END