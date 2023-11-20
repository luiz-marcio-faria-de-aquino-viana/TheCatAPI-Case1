/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * AppDefs.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1;

public class AppDefs 
{
//Public

	//DEBUG_LEVEL
	//
	public static final int DEBUG_LEVEL_INFO = 0;
	public static final int DEBUG_LEVEL_ERROR = 1;
	public static final int DEBUG_LEVEL_WARN = 2;
	public static final int DEBUG_LEVEL_DEBUG = 3;
	
	//public static final int DEBUG_LEVEL = AppDefs.DEBUG_LEVEL_ERROR;

	//DEBUG_LEVEL_STR
	//
	public static final String[] DEBUG_LEVEL_ARR = { "INFO", "ERROR", "WARN", "DEBUG" };
	public static final Integer DEBUG_LEVEL_ARRSZ = AppDefs.DEBUG_LEVEL_ARR.length;
	
	//DEBUG_OUTPUT_MODE
	//
	public static final int DEBUG_OUTPUT_MODE_STDOUT = 0;
	public static final int DEBUG_OUTPUT_MODE_STDERR = 1;
	public static final int DEBUG_OUTPUT_MODE_LOG_FILE = 2;
	
	//DEBUG_OUTPUT_MODE_STR
	//
	public static final String[] DEBUG_OUTPUT_MODE_ARR = { "STDOUT", "STDERR", "LOG_FILE" };
	public static final Integer DEBUG_OUTPUT_MODE_ARRSZ = AppDefs.DEBUG_OUTPUT_MODE_ARR.length;
	
	//APPLICATION_NAME
	//
	public static final String APP_NAME = "TheCatAPICase1"; 
	
	public static final String APP_VERSAO = "1.2.20221030";

	public static final String APP_COPYRIGHT =
		"Copyright(C) 2022 TLMV Consultoria e Sistemas EIRELI\n" +
		"Autor: Luiz Marcio Faria de Aquino Viana, Pos-D.Sc.\n" +
		"E-mail: luiz.marcio.viana@gmail.com\n" +
		"Telefone: +55-21-99983-7207\n";

	public static final String APP_ICON = "/br/com/tlmv/thecatapicase1/res/thecatapicase1app_48x48.png";

	public static final String APP_HOME = "THECATAPICASE1_HOME";
	
	//DEFAULT_CONTEXT_VALUES
	//
	public static final String CTX_DEFAULT_HOME_DIR = "/home/lmarcio/101-UFRJ/9999-APRESENTACAO-LUIZ_MARCIO_FARIA_DE_AQUINO_VIANA-CPF02472334710/101-TESTES_PROGRAMACAO/101-ITAU_UNIBANCO-THE_CAT_API_CASE1";
	//public static final String CTX_DEFAULT_HOME_DIR = "/home/lmarcio/TheCatAPICase1";
	
	public static final String CTX_HOME_BIN = "/Bin";
	public static final String CTX_HOME_CONFIG = "/Config";
	public static final String CTX_HOME_DATA = "/Data";
	public static final String CTX_HOME_DATA_IMAGES = "/Data/Images";
	public static final String CTX_HOME_DATA_CATSWITHHAT = "/Data/CatsWithHat";
	public static final String CTX_HOME_DATA_CATSWITHGLASSES = "/Data/CatsWithGlasses";
	public static final String CTX_HOME_LOGS = "/Logs";

	public static final String CTX_CONFIG_FILENAME = "thecatapicase1_appconfig.xml";
	public static final String CTX_LOG_FILENAME = "thecatapicase1.log";

	//DEFAULT_CONFIG_TAGS
	//
	public static final String CFG_DEBUG_LEVEL = "DebugLevel";
	public static final String CFG_DEBUG_OUTPUT_MODE = "DebugOutputMode";
	public static final String CFG_DEBUG_LOCAL_FILE_NAME = "DebugLocalFileName";
	
	//INET_PROTOCOL
	//
	public static final String DEF_HTTP_PROTOCOL = "http";
	public static final String DEF_HTTPS_PROTOCOL = "https";
	
	//REQUEST_METHOD
	//
	public static final String DEF_REQUEST_METHOD_GET = "GET";
	public static final String DEF_REQUEST_METHOD_POST = "POST";
	public static final String DEF_REQUEST_METHOD_PUT = "PUT";
	public static final String DEF_REQUEST_METHOD_DELETE = "DELETE";
	
	//REQUEST_RETRY
	public static final Integer DEF_REQUEST_MAXRETRY = 3;	// max number of retry after request failure 

	//HTTP_RESPONSE_CODES
	public static final Integer ERR_HTTP_RESPCODE_INFORMATIONAL = 100;
	public static final Integer ERR_HTTP_RESPCODE_SUCCESSFUL = 200;
	public static final Integer ERR_HTTP_RESPCODE_REDIRECTION = 300;
	public static final Integer ERR_HTTP_RESPCODE_CLIENT_ERROR = 400;
	public static final Integer ERR_HTTP_RESPCODE_CLIENT_ERROR_TOO_MANY_RETRY = 429;
	public static final Integer ERR_HTTP_RESPCODE_SERVER_ERROR = 500;
	
	//CONNECTION_TIMEOUT
	//
	public static final Integer DEF_CONNECTION_TIMEOUT = 30 * 1000;
	public static final Integer DEF_READ_TIMEOUT = 60 * 1000;
	
	//FILE_READ_BUFFER_SIZE
	//
	public static final Integer DEF_FILE_READ_BUFFER_SIZE = 4096;
		
	//SSL_CONTEXT
	//
	public static final String DEF_DEFAULT_SSL_CONTEXT = "SSL";
	
	//THE_CAT_API - API_KEY / USER_AGENT
	//
	public static final String DEF_API_KEY = "live_r59AnbBhmCQeMk6lx25IdesqUTnn4HlB7ulsRptD7iZgsgVKgUlqXjSIXXHE3GJr";
	public static final String DEF_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36";
	
	//WEBSERVICE_ENDPOINT
	//
	//CATEGORIES
	public static final String WS_ENDPOINT_CATEGORIES = "https://api.thecatapi.com/v1/categories";
	//BREEDS
	public static final String WS_ENDPOINT_BREEDS = "https://api.thecatapi.com/v1/breeds";
	//IMAGES
	public static final String WS_ENDPOINT_IMAGES_SEARCH = "https://api.thecatapi.com/v1/images/search";
	public static final String WS_ENDPOINT_IMAGES_SEARCH10 = "https://api.thecatapi.com/v1/images/search?limit=10";
	public static final String WS_ENDPOINT_IMAGES_BY_BREED_SEARCH100 = "https://api.thecatapi.com/v1/images/search?limit=%s&breed_ids=%s&api_key=%s";
	public static final String WS_ENDPOINT_IMAGES_BY_CATEGORY_SEARCH100 = "https://api.thecatapi.com/v1/images/search?limit=%s&category_ids=%s&api_key=%s";

	//WEBSERVICE_LIMITS
	//
	public static final String WS_MAX_SEARCH_LIMIT = "100";

	//SERVICE_THREADS
	public static final Integer THREAD_MAX_WORKERS 			= 5;				/* 5 workes per query */
	public static final Integer THREAD_SLEEP_TIME 			= 100;				/* 100 milisecond */
	public static final Integer THREAD_FAILURE_SLEEP_TIME	= 5 * 1000;			/* 5 segundo */
	
	//COMMAND_SWITCH - STRING
	public static final String CMD_HELP_STR = "-help";
	public static final String CMD_TEST_STR = "-test";
	public static final String CMD_DATACREATOR_STR = "-creator";
	public static final String CMD_RESTSERVER_STR = "-rest";

	//COMMAND_SWITCH - VALUES
	public static final Integer CMD_HELP_VAL = 1;
	public static final Integer CMD_TEST_VAL = 2;
	public static final Integer CMD_DATACREATOR_VAL = 4;
	public static final Integer CMD_RESTSERVER_VAL = 8;
	
	//USAGE_INFO
	//
	public static final String HLP_USAGE_INFO =
		    "AJUDA\n\n" +
			"Use: thecatapicase1 -help                          - para apresentar estas informacoes de ajuda.\n" +
			" ou  thecatapicase1 -test                          - para executar os testes dos componentes da aplicacao.\n" +	
			" ou  thecatapicase1 -creator                       - para criar os arquivos de dados do servico 'The Cat API'.\n" +	
			" ou  thecatapicase1 -rest                          - para iniciar o servidor RESTfull usando a base de dados local.\n";	
	
	//FILE_EXTENSIONS
	public static final String EXT_TMP = ".$";
	public static final String EXT_DAT = ".dat";
	public static final String EXT_JPG = ".jpg";
	public static final String EXT_JPEG = ".jpeg";
	public static final String EXT_PNG = ".png";
	public static final String EXT_HTML = ".html";
	public static final String EXT_LOG = ".log";
	
	public static final String MIMETYPE_XML = "application/soap+xml";
	public static final String MIMETYPE_JPG = "image/jpeg";
	public static final String MIMETYPE_JPEG = "image/jpeg";
	public static final String MIMETYPE_PNG = "image/png";
	public static final String MIMETYPE_HTML = "text/html";
	public static final String MIMETYPE_BLOB = "binary/octet-stream";
	
	//BASE_OBJECT_ID
	public static final String BASE_OBJID_NONE = "-1";

	//BASE_OBJECT_TYPE
	public static final Integer BASE_OBJTYPE_NONE = -1;
	public static final Integer BASE_OBJTYPE_BREED = 1001;
	public static final Integer BASE_OBJTYPE_BREEDWEIGHT = 1002;
	public static final Integer BASE_OBJTYPE_BREEDIMAGE = 1003;
	public static final Integer BASE_OBJTYPE_IMAGE = 1004;
	public static final Integer BASE_OBJTYPE_CATEGORY = 1005;
	
	//BASE_TABLE_NAME
	public static final String BASE_TBLNAME_CATEGORY = "tbl_category";
	public static final String BASE_TBLNAME_BREED = "tbl_breed";
	public static final String BASE_TBLNAME_IMAGE = "tbl_image";
	public static final String BASE_TBLNAME_CATSWITHGLASSES = "tbl_catswithglasses";
	public static final String BASE_TBLNAME_CATSWITHHAT = "tbl_catswithhat";
	
	//DATE_FORMAT
	public static final String FMT_DATE_MASC = "dd/MM/yyyy"; 
	public static final String FMT_DATETIME_MASC = "dd/MM/yyyy HH:mm:ss"; 
	public static final String FMT_DATE_INV_MASC = "yyyy-MM-dd"; 
	public static final String FMT_DATETIME_INV_MASC = "yyyy-MM-dd_HH:mm:ss"; 
	
	//CATEGORY_IDS
	public static final Integer DEF_CATEGORY_HATS = 1;
	public static final Integer DEF_CATEGORY_SPACE = 2;
	public static final Integer DEF_CATEGORY_SUNGLASSES = 4;
	public static final Integer DEF_CATEGORY_BOXES = 5;
	public static final Integer DEF_CATEGORY_TIES = 7;
	public static final Integer DEF_CATEGORY_SINKS = 14;
	public static final Integer DEF_CATEGORY_CLOTHES = 15;
	
	//REST_SERVER_STR
	public static final String REST_CMD_COPYRIGHT_STR = "/TheCatAPICase1/copyright";
	//
	public static final String REST_CMD_LISTALL_BREEDS_STR = "/TheCatAPICase1/breeds/listall";
	public static final String REST_CMD_LIST_BREED_STR = "/TheCatAPICase1/breed/?";
	public static final String REST_CMD_LIST_BREEDS_BY_TEMPERAMENT_STR = "/TheCatAPICase1/breeds/listby/temperament/?";
	public static final String REST_CMD_LIST_BREEDS_BY_ORIGIN_STR = "/TheCatAPICase1/breeds/listby/origin/?";
	//
	public static final String REST_CMD_LISTALL_IMAGES_STR = "/TheCatAPICase1/images/listall";
	public static final String REST_CMD_LIST_IMAGE_STR = "/TheCatAPICase1/image/?";
	public static final String REST_CMD_LIST_IMAGES_BY_CATWITHGLASSES_STR = "/TheCatAPICase1/images/listby/catswithglasses";
	public static final String REST_CMD_LIST_IMAGES_BY_CATWITHHAT_STR = "/TheCatAPICase1/images/listby/catswithhat";
	//
	public static final String REST_CMD_DOWNLOAD_IMAGE_STR = "/TheCatAPICase1/download/image/?";
	//
	public static final String REST_CMD_LIST_IMAGES_BY_BREED_STR = "/TheCatAPICase1/images/listby/breed/?";
	public static final String REST_CMD_LIST_IMAGES_BY_TEMPERAMENT_STR = "/TheCatAPICase1/images/listby/temperament/?";
	public static final String REST_CMD_LIST_IMAGES_BY_ORIGIN_STR = "/TheCatAPICase1/images/listby/origin/?";
	//
	public static final String REST_CMD_DEFAULT_STR = AppDefs.REST_CMD_COPYRIGHT_STR;
	
	//REST_SERVER_VAL
	public static final Integer REST_CMD_COPYRIGHT_VAL = 1001;
	//
	public static final Integer REST_CMD_LISTALL_BREEDS_VAL = 2001;
	public static final Integer REST_CMD_LIST_BREED_VAL = 2002;
	public static final Integer REST_CMD_LIST_BREEDS_BY_TEMPERAMENT_VAL = 2003;
	public static final Integer REST_CMD_LIST_BREEDS_BY_ORIGIN_VAL = 2004;
	//
	public static final Integer REST_CMD_LISTALL_IMAGES_VAL = 3001;
	public static final Integer REST_CMD_LIST_IMAGE_VAL = 3002;
	public static final Integer REST_CMD_LIST_IMAGES_BY_CATWITHGLASSES_VAL = 3003;
	public static final Integer REST_CMD_LIST_IMAGES_BY_CATWITHHAT_VAL = 3004;
	//
	public static final Integer REST_CMD_DOWNLOAD_IMAGE_VAL = 4001;
	//
	public static final Integer REST_CMD_LIST_IMAGES_BY_BREED_VAL = 5001;
	public static final Integer REST_CMD_LIST_IMAGES_BY_TEMPERAMENT_VAL = 5002;
	public static final Integer REST_CMD_LIST_IMAGES_BY_ORIGIN_VAL = 5003;
	//
	public static final Integer REST_CMD_DEFAULT_VAL = AppDefs.REST_CMD_COPYRIGHT_VAL;
	
	//DEF_REST_SERVER
	public static final Long DEF_REST_INITIAL_REQUEST_ID = 1000000L;
	public static final Integer DEF_REST_MAX_NUM_WORK_THREADS = 10;
	public static final Integer DEF_REST_REQUEST_QUEUE_SIZE = 100;
	public static final String DEF_REST_SERVER_ADDR = "127.0.0.1";
	public static final Integer DEF_REST_SERVER_PORT = 9090;
	public static final String DEF_REST_APPLICATION_CONTEXT = "/TheCatAPICase1";
	public static final Integer DEF_REST_SERVER_STOP_DELAY = 5;		// server stop delay time in seconds
		
}
