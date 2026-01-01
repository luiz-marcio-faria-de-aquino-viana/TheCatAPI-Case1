TheCatAPICase1 1.2.20221031

Copyright(C) 2020-2022 TLMV Consultoria e Sistemas EIRELI
Autor: Luiz Marcio Faria de Aquino Viana, Pos-D.Sc.
E-mail: luiz.marcio.viana@gmail.com
Telefone: +55-21-99983-7207

File: README.md
Data: 31/10/2022

1. INFORMAÇÕES:

(a) A aplicacao "TheCatAPICase1" foi desenvolvida usando somente os recursos existentes na linguagem Java 2 versão 1.8 e superiores.

(b) A única biblioteca externa utilizada foi: json-20090211.jar

(c) As tabelas de dados são do tipo chave-valor em memória.

(d) Os objetos foram persistidos através da serialização dos dados em disco.

(e) O processo de criacao das tabelas é executado de forma sequencial, porque o servico fornecido pelo TheCatAPI possui controle de requisições.

(f) Ao acessar o servico fornecido pelo TheCatAPI, ocorreu ocasionalmente a seguinte excessão: HTTP/1.1 429 Too Many Requests
- Esta excessão foi tratada com uma espera de 5 segundos, e uma nova tentativa de receber os dados (MAX=5 Tentativas). 

(g) Ao buscar a image "https://cdn2.thecatapi.com/images/p2U4ZXgKL.jpg", ocorreu a excessão "Unsuported Image Format" na classe ImageIO devido ao formato de compressão da imagem.
- Esta excessão NAO foi resolvida (ver a lista de bugs conhecidos). 

(h) A interface RESTfull foi implementada usando HttpServer existente no proprio Java 2.

(i) Todas as consultas realizadas pela interface RESTfull dividem os dados das tabelas em 5 partes, e executam a consulta usando 5 threads de processamento.
- Como todas as operacoes sao de consulta, e as tabelas foram segmentadas, NAO existe concorrencia no acesso aos dados, e nenhum metodo de acesso as tabelas foi sincronizado (LOCK).

(j) A aplicacao permite o monitoramento atraves de mensagens de log armazenadas em arquivo ou apresentadas no terminal.

(k) Existem 4 (quatro) niveis de registro de mensagens que pode ser ajustado em um arquivo de configuracoes: ./Config/thecatapicase1_appconfig.xml

	<?xml version="1.0" encoding="ISO-8859-1"?>
	<Configuracao>
		<!--
		// DebugLevel: 		INFO		- only info messages are logged
		//			ERROR 		- info and errors messages are logged
		//	       		WARN  		- info, errors and warning messages are logged
		//	       		DEBUG 		- info, errors, warning and debug messages are logged
		-->
		<DebugLevel>DEBUG</DebugLevel>
		<!--
		// DebugOutputMode: 	STDOUT 		- output log messages to standard output
		//                  	STDERR 	    	- output log messages to standard error
		//		    	LOG_FILE 	- output log messages to a file located into ./Logs directory
		-->
		<DebugOutputMode>LOG_FILE</DebugOutputMode>
	</Configuracao>

(l) Diretorios:

	TheCatAPICase1 (Home) 	+- Bin		* localizacao do arquivo .jar, das bibliotecas usadas, do Java 2 Runtime, e dos scripts de execucao.
			      	|
				+- Config	* localizacao do arquivo de configuracao: thecatapicase1_appconfig.xml
				|
				+- Data		* diretorio de persistencia das tabelas e arquivos de imagens.
			      	|
				+- Docs		* diretorio de documentos.
				|
				+- Logs		* diretorio de mensagens de logs.
			      	|
				+- ServerData	* diretorio BACKUP do diretorio DATA (PERMITE INICIAR O SERVIDOR RESTfull SEM SOLICITAR OS DADOS).

(m) Parametros de linha de comando:

	AJUDA

	Use: thecatapicase1 -help                          	- para apresentar estas informações de ajuda.
	 ou  thecatapicase1 -test                          	- para executar os testes dos componentes da aplicação.
	 ou  thecatapicase1 -creator                       	- para criar os arquivos de dados do serviço 'The Cat API'.
	 ou  thecatapicase1 -rest                          	- para iniciar o servidor RESTfull usando a base de dados local.

2. ARQUITETURA DO PROJETO

2.1. PACOTES E CLASSES:

	+- br.com.tlmv.thecatapicase1
	|	AppDefs						- Definição das constantes da aplicação.
	|	AppContext					- Contexto da aplicação (diretórios estáticos da aplicação).
	|	AppConfig					- Configurações da aplicação, definidas no arquivo: ./Config/thecatapicase1_appconfig.xml
	|	AppError					- Processamento do registro de logs da aplicação e definição das mensagens de erro.
	|	AppDataManager					- Gerenciamento da persistência do dados das tabelas (saveAll/loadAll).
	|	AppMain						- Classe principal da aplicação, e que possui o método estático main().
	|	
	+- br.com.tlmv.thecatapicase1.data
	|	BaseObject					- Objeto base usado pelas classes do pacote: br.com.tlmv.thecatapicase1.data
	|	Breed						- Value Object: derivada de BaseObject. Armazena dados do tipo Breed.
	|	BreedImage					- Value Object: derivada de BaseObject. Armazena dados do tipo BreedImage.
	|	BreedWeight					- Value Object: derivada de BaseObject. Armazena dados do tipo BreedWeight.
	|	Category					- Value Object: derivada de BaseObject. Armazena dados do tipo Category.
	|	Image						- Value Object: derivada de BaseObject. Armazena dados do tipo Image.
	|	
	+- br.com.tlmv.thecatapicase1.datacreator
	|	DataCreatorObject				- Classe base usado pelas classes do pacote: br.com.tlmv.thecatapicase1.datacreator.
	|	DataCreatorBreed				- Service: derivada de DataCreatorObject. Busca e armazena os dados Breed.
	|	DataCreatorCategory				- Service: derivada de DataCreatorObject. Busca e armazena os dados Category.
	|	DataCreatorCatsWithGlasses			- Service: derivada de DataCreatorObject. Busca e armazena os dados CatsWithGlasses (Image).
	|	DataCreatorCatsWithHat				- Service: derivada de DataCreatorObject. Busca e armazena os dados CatsWithHat (Image).
	|	DataCreatorImage				- Service: derivada de DataCreatorObject. Busca e armazena os dados Image.
	|	ExecDataCreator					- Executor: classe de execução das classes deste pacote e criação das tabelas (-creator).
	|	
	+- br.com.tlmv.thecatapicase1.nosql
	|	BaseTable					- Classe base usado pelas classes do pacote: br.com.tlmv.thecatapicase1.nosql.
	|	BreedTable					- Utility: derivada de BaseTable. Armazena os dados Breed usando Hashtable.
	|	CategoryTable					- Utility: derivada de BaseTable. Armazena os dados Category usando Hashtable.
	|	CatsWithGlassesTable				- Utility: derivada de BaseTable. Armazena os dados CatsWithGlasses (Image) usando Hashtable.
	|	CatsWithHatTable				- Utility: derivada de BaseTable. Armazena os dados CatsWithHat (Image) usando Hashtable.
	|	ImageTable					- Utility: derivada de BaseTable. Armazena os dados Image usando Hashtable.
	|	
	+- br.com.tlmv.thecatapicase1.nosql.query
	|	QueryExecutor					- Dispatcher: classe de preparação dos dados e dispacho das threads de execução.
	|	QueryWorker					- Worker: classe de processamento dos dados em uma nova thread.
	|	
	+- br.com.tlmv.thecatapicase1.res
	|	thecatapicase1app_48x48.png			- Icone da aplicação (NÃO usado em aplicações de linha de comando).
	|
	+- br.com.tlmv.thecatapicase1.rest
	|	RESTfullServer					- Utility: classe de inicialização do servidor HttpServer existente no Java 2.
	|	RESTfullCommandHandler				- Handler: classe de mapeamento das requisições HTTP em uma classe de processamento.
	|	RESTfullCommand					- Value Object: classe de dados que armazena a requisição recebida pelo Handler
	|	RESTfullCommandParser				- Utility: classe de reconhecimento do comando e parâmetros recebidos na requisição. 
	|	RESTfullCommandExecutor				- Executor: classe de execução da requisição HTTP (-rest).
	|	
	+- br.com.tlmv.thecatapicase1.service
	|	BaseService					- Classe base usado pelas classes do pacote: br.com.tlmv.thecatapicase1.service.
	|	BreedService					- Service: derivada de BaseService. Busca os dados sobre Breed no serviço TheCatAPI.
	|	CategoryService					- Service: derivada de BaseService. Busca os dados sobre Category no serviço TheCatAPI.
	|	ImageService					- Service: derivada de BaseService. Busca os dados sobre Image no serviço TheCatAPI.
	|	
	+- br.com.tlmv.thecatapicase1.tests
	|	TestBase					- Classe base usado pelas classes do pacote: br.com.tlmv.thecatapicase1.tests.
	|	TestBreedService				- Executor: classe de execução dos testes unitários de BreedService. 
	|	TestBreedTable					- Executor: classe de execução dos testes unitários de BreedTable.
	|	TestCategoryService				- Executor: classe de execução dos testes unitários de CategoryService.
	|	TestCategoryTable				- Executor: classe de execução dos testes unitários de CategoryTable.
	|	TestImageService				- Executor: classe de execução dos testes unitários de ImageService.
	|	TestImageTable					- Executor: classe de execução dos testes unitários de ImageTable.
	|	UnitTest					- Executor: classe de execução dos testes unitários (-test).
	|	
	+- br.com.tlmv.thecatapicase1.utils
	|	FileUtil					- Utility: classe utilitária de acesso a arquivos.
	|	JSONUtil					- Utility: classe utilitária de manipulação de objetos JSON.
	|	StringUtil					- Utility: classe utilitária de manipulação de cadeia de caracteres.
	|	UuidUtil					- Utility: classe utilitária de geração de identificadores.
	|	XmlUtil						- Utility: classe utilitária de manipulação de arquivos XML.
	|	


3. APIs USADAS NESTE PROJETO

3.1. JSON - JavaScript Object Notation (https://www.json.org/json-en.html)
- Para manipulação dos dados recebidos no formato JSON, foi utilizado a biblioteca: json-20090211.jar
- Os dados recebidos no formato textual JSON, são convertidos nas classes JSONObject ou JSONArray (quando um recebemos um vetor de elementos).
- Foi desenvolvida a classe utilitária JSONUtil, que permite a conversão segura dos dados do formato JSON para propriedade das classes Value Object.

(a) JSONObject		- Objeto básico da classe JSON. Possui métodos para obter dados numéricos e de cadeias de caracteres.

(b) JSONArray		- Vetor de objetos da classe JSON. Possui métodos para percorrer os objetos do vetor.

3.2. TheCatAPI (https://www.thecatapi.com/)

(a) CATEGORIES 	- API que lista todas as categorias disponíveis no serviço TheCatAPI.

REQUISIÇÃO: https://api.thecatapi.com/v1/categories

RESPOSTA:

[{"id":"4","name":"sunglasses"},{"id":"2","name":"space"},{"id":"1","name":"hats"},{"id":"7","name":"ties"}]

(b) BREEDS	- API que lista todos as raças disponíveis no serviço TheCatAPI. 

REQUISIÇÃO: https://api.thecatapi.com/v1/breeds

RESPOSTA:

{ "weight":{ "weight":{ "imperial":"8 - 16","metric":"4 - 7" } },"id":"sibe","name":"Siberian","cfa_url":"Siberian","vetstreet_url":"http://www.vetstreet.com/cats/siberian","vcahospitals_url":"https://vcahospitals.com/know-your-pet/cat-breeds/siberian","temperament":"Curious, Intelligent, Loyal, Sweet, Agile, Playful, Affectionate","origin":"Russia","country_codes":"RU","country_code":"RU","description":"The Siberians dog like temperament and affection makes the ideal lap cat and will live quite happily indoors. Very agile and powerful, the Siberian cat can easily leap and reach high places, including the tops of refrigerators and even doors. ","life_span":"12 - 15","indoor":0,"lap":"0,"alt_names":"Moscow Semi-longhair, HairSiberian Forest Cat","adaptability":5,"affection_level":5,"child_friendly":4,"dog_friendly":5,"energy_level":5,"grooming":2,"health_issues":2,"intelligence":5,"shedding_level":3,"social_needs":4,"stranger_friendly":3,"vocalisation":1,"experimental":0,"hairless":0,"natural":1,"rare":0,"rex":0,"suppressed_tail":0,"short_legs":0,"wikipedia_url":"https://en.wikipedia.org/wiki/Siberian_(cat)","hypoallergenic":1,"reference_image_id":"3bkZAjRh1","image":{ "image":{ "id":"3bkZAjRh1","width":4232,"height":2560,"url":"https://cdn2.thecatapi.com/images/3bkZAjRh1.jpg" } } }

(c) IMAGES	- API que lista dados das imagens disponíveis no serviço TheCatAPI.

REQUISIÇÃO:

	https://api.thecatapi.com/v1/images/search					- busca uma imagem aleatoriamente.
	https://api.thecatapi.com/v1/images/search?limit=10				- busta dez imagens aleatoriamente.
	https://api.thecatapi.com/v1/images/search?limit=%s&breed_ids=%s&api_key=%s	- busca ate 100 imagens em sequencia, por raça.
	https://api.thecatapi.com/v1/images/search?limit=%s&category_ids=%s&api_key=%s	- busca até 100 imagens em sequencia, por categoria.

RESPOSTA:

[ { "id":"e9GFNGkwq","width":1280,"height":960,"url":"https://cdn2.thecatapi.com/images/e9GFNGkwq.jpg","local_file":"e9GFNGkwq.jpg","breeds":[ { { "weight":{ "weight":{ "imperial":"6 - 12","metric":"3 - 5" } },"id":"tonk","name":"Tonkinese","cfa_url":"Tonkinese","vetstreet_url":"http://www.vetstreet.com/cats/tonkinese","vcahospitals_url":"https://vcahospitals.com/know-your-pet/cat-breeds/tonkinese","temperament":"Curious, Intelligent, Social, Lively, Outgoing, Playful, Affectionate","origin":"Canada","country_codes":"CA","country_code":"CA","description":"Intelligent and generous with their affection, a Tonkinese will supervise all activities with curiosity. Loving, social, active, playful, yet content to be a lap cat","life_span":"14 - 16","indoor":0,"lap":"0,"alt_names":"Tonk","adaptability":5,"affection_level":5,"child_friendly":4,"dog_friendly":5,"energy_level":5,"grooming":1,"health_issues":1,"intelligence":5,"shedding_level":3,"social_needs":5,"stranger_friendly":5,"vocalisation":5,"experimental":0,"hairless":0,"natural":0,"rare":0,"rex":0,"suppressed_tail":0,"short_legs":0,"wikipedia_url":"https://en.wikipedia.org/wiki/Tonkinese_(cat)","hypoallergenic":0,"reference_image_id":"KBroiVNCM","image":{ "image":{ "id":"-1","width":0,"height":0,"url":"" } } } } ] } ]

4. EXEMPLO DE TESTES DO SERVIDOR RESTfull USANDO O COMANDO CURL

	curl http://127.0.0.1:9090/TheCatAPICase1/copyright				- apresenta uma mensagem de copyright
	curl http://127.0.0.1:9090/TheCatAPICase1/breeds/listall			- lista todas as raças de gatos
	curl http://127.0.0.1:9090/TheCatAPICase1/breed/sibe				- obtém somente uma raça de gato (BREED=sibe)
	curl http://127.0.0.1:9090/TheCatAPICase1/breeds/listby/origin/Russia		- obtém todas as raças de gatos de uma origem (=Russia)
	curl http://127.0.0.1:9090/TheCatAPICase1/breeds/listby/origin/Egypt		- obtém todas as raças de gatos de uma origem (=Egypt)
	curl http://127.0.0.1:9090/TheCatAPICase1/breeds/listby/temperament/Curious	- obtém todas as raças de gatos com um temperamento (=Curious)
	curl http://127.0.0.1:9090/TheCatAPICase1/breeds/listby/temperament/Intelligent	- obtém todas as raças de gatos com um temperamento (=Intelligent)
	curl http://127.0.0.1:9090/TheCatAPICase1/images/listall			- obtém os dados de todas as imagens de gatos
	curl http://127.0.0.1:9090/TheCatAPICase1/images/listby/catswithglasses		- obtém os dados de todas as imagens de gatos com óculos
	curl http://127.0.0.1:9090/TheCatAPICase1/images/listby/catswithhat		- obtém os dados de todas as imagens de gatos com chapéu
	curl http://127.0.0.1:9090/TheCatAPICase1/image/36v				- obtém os dados de uma imagem (=36v)

	curl -o 3ar.jpg http://127.0.0.1:9090/TheCatAPICase1/download/image/3ar		- download da imagem (=3ar) e armazenamento no disco local
	curl -o bk.jpg http://127.0.0.1:9090/TheCatAPICase1/download/image/bk		- download da imagem (=bk) e armazenamento no disco local

5. INSTALACAO:

(a) Copie o diretorio HOME da aplicacao para o seu diretorio de trabalho.

(b) Abra os arquivos shell script localizados na pasta ./TheCatAPICase1/Bin e altere a localizacao do diretorio HOME.

	run_thecatapicase1_help.sh		* apresenta o menu de ajuda
	run_thecatapicase1_test.sh		* executa a aplicacao em modo de teste
	run_thecatapicase1_creator.sh		* executa a aplicacao em modo de creacao da base de dados
	run_thecatapicase1_rest.sh		* executa a aplicacao em modo servidor RESTfull (recria as tabelas a partir do BACKUP)
	run_thecatapicase1_rest_noerasedata.sh	* executa a aplicacao em modo servidor RESTfull (NAO RECRIA AS TABELAS)

	Ex:	
	APP_HOME=/home/lmarcio/TheCatAPICase1

	Para:
	APP_HOME=/home/RENAN/TheCatAPICase1

6. EXECUÇÃO:
	
(a) Execute a aplicação no modo servidor RESTfull, e use o navegador de internet para efetuar os testes abaixo.

	./TheCatAPICase1/Bin/run_thecatapicase1_rest.sh

	http://127.0.0.1:9090/TheCatAPICase1/copyright
	http://127.0.0.1:9090/TheCatAPICase1/breeds/listall
	http://127.0.0.1:9090/TheCatAPICase1/breed/sibe
	http://127.0.0.1:9090/TheCatAPICase1/breeds/listby/origin/Russia
	http://127.0.0.1:9090/TheCatAPICase1/breeds/listby/origin/Egypt
	http://127.0.0.1:9090/TheCatAPICase1/breeds/listby/temperament/Curious
	http://127.0.0.1:9090/TheCatAPICase1/breeds/listby/temperament/Intelligent
	http://127.0.0.1:9090/TheCatAPICase1/images/listall
	http://127.0.0.1:9090/TheCatAPICase1/images/listby/catswithglasses
	http://127.0.0.1:9090/TheCatAPICase1/images/listby/catswithhat
	http://127.0.0.1:9090/TheCatAPICase1/image/36v
	http://127.0.0.1:9090/TheCatAPICase1/download/image/3ar
	http://127.0.0.1:9090/TheCatAPICase1/download/image/bk

(b) Execute a aplicação no modo de verificação dos testes unitarios.

	./TheCatAPICase1/Bin/run_thecatapicase1_test.sh

(c) Execute a aplicação no modo de criacao da base de dados a partir do conteudo do site https://www.thecatapi.com.

	./TheCatAPICase1/Bin/run_thecatapicase1_creator.sh

7. BUGS CONHECIDOS

(a) Falha no download das imagens abaixo devido ao formato de compressao da imagem nao ser suportado pelo classe ImageIO do Java 2 versão 1.8.

Unsuported Image Format
https://cdn2.thecatapi.com/images/p2U4ZXgKL.jpg?api_key=live_r59AnbBhmCQeMk6lx25IdesqUTnn4HlB7ulsRptD7iZgsgVKgUlqXjSIXXHE3GJr

8. DÚVIDAS:

(a) Duvidas ou sugestoes entre em contato por e-mail ou telefone.

	Contato: Luiz Marcio Faria de Aquino Viana, Pos-D.Sc. (Autor)
	E-mail: luiz.marcio.viana@gmail.com
	Telefone: +55-21-99983-7207 - WhatsApp: +55-21-95911-5253	

# CONTATO

CONTACT ME, IF YOU NEED HELP OR HAVE ANY QUESTIONS ABOUT THIS ACADEMIC WORK!

Luiz Marcio Faria de Aquino Viana,Pós-D.Sc.

E-mail: luiz.marcio.viana@gmail.com

Phone: +55-21-99983-7207 - WhatsApp: +55-21-95911-5253
