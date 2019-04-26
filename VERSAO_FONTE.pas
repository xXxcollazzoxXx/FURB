unit VERSAO_FONTE;

interface

uses System.Classes
   ;

Type
  TVERSAO_FONTE = Class
    private
      fID : Integer;
      fOBSERVACAO : String;
      fVERSAO : Integer;
      fDATA_GRAVACAO : TDateTime;
      fARQUIVO_BLOB : TMemoryStream;

      procedure setID( liID : Integer );
      function getID : Integer;
      procedure setOBSERVACAO( lsOBSERVACAO : String );
      function getOBSERVACAO : String;
      procedure setVERSAO( liVERSAO : Integer );
      function getVERSAO : Integer;
      procedure setDATA_GRAVACAO( ldDATA_GRAVACAO : TDateTime );
      function getDATA_GRAVACAO : TDateTime;
      procedure setARQUIVO_BLOB( loARQUIVO_BLOB : TMemoryStream );
      function getARQUIVO_BLOB : TMemoryStream;

    public
      constructor create;
      destructor destroy; override;

      Property ID            : Integer       read getID            write setID;
      Property OBSERVACAO    : String        read getOBSERVACAO    write setOBSERVACAO;
      Property VERSAO        : Integer       read getVERSAO        write setVERSAO;
      Property DATA_GRAVACAO : TDateTime     read getDATA_GRAVACAO write setDATA_GRAVACAO;
      Property ARQUIVO_BLOB  : TMemoryStream read getARQUIVO_BLOB  write setARQUIVO_BLOB;
  End;


implementation

Uses SysUtils
   ;

{ TVERSAO_FONTE }

constructor TVERSAO_FONTE.create;
begin
  self.fID            := 0;
  Self.fOBSERVACAO    := EmptyStr;
  self.fVERSAO        := 0;
  self.fDATA_GRAVACAO := now;
  self.fARQUIVO_BLOB  := nil;
end;

destructor TVERSAO_FONTE.destroy;
begin
  inherited;
end;

function TVERSAO_FONTE.getARQUIVO_BLOB: TMemoryStream;
begin
  Result := self.fARQUIVO_BLOB;
end;

function TVERSAO_FONTE.getDATA_GRAVACAO: TDateTime;
begin
  Result := fDATA_GRAVACAO;
end;

function TVERSAO_FONTE.getID: Integer;
begin
  Result := self.fID
end;

function TVERSAO_FONTE.getOBSERVACAO: String;
begin
  Result := self.fOBSERVACAO;
end;

function TVERSAO_FONTE.getVERSAO: Integer;
begin
  Result := self.fVERSAO;
end;

procedure TVERSAO_FONTE.setARQUIVO_BLOB(loARQUIVO_BLOB: TMemoryStream);
begin
  self.fARQUIVO_BLOB := loARQUIVO_BLOB;
end;

procedure TVERSAO_FONTE.setDATA_GRAVACAO(ldDATA_GRAVACAO: TDateTime);
begin
  self.fDATA_GRAVACAO := ldDATA_GRAVACAO;
end;

procedure TVERSAO_FONTE.setID(liID: Integer);
begin
  self.ID := liID;
end;

procedure TVERSAO_FONTE.setOBSERVACAO(lsOBSERVACAO: String);
begin
  Self.fOBSERVACAO := lsOBSERVACAO;
end;

procedure TVERSAO_FONTE.setVERSAO(liVERSAO: Integer);
begin
  Self.fVERSAO := liVERSAO;
end;

end.
