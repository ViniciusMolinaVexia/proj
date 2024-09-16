**API - RM Online Andrade Gutierrez**

Abaixo segue informações referente ao build ou processos para manutenção da API.

---

## Gerar classes do consumer wsdl

1. Para gerar as classes de cliente do WSDL gerado pela AGNET faça os seguintes passos;
2. Acessar a pasta do projeto de API através do comando abaixo:

$ cd ~/projects/api/rmonline-ag-api-v1/src/main/resources

3. Nesta pasta coloque o arquivo .xml ou .wsdl enviado pelo time do SAP;

zgl_rmaweb.xml

4. com o arquivo atualizado na pasta basta rodar o comando abaixo:

$ wsimport -keep -verbose zgl_rmaweb.xml -d ~/projects/api/rmonline-ag-api-v1/src/main/java