package com.kokonut.backend.config;

public class JwtConfig {
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwK0jCF1j7fUiJO1XYgn6\r\n" + 
			"9dBbPdl9PdgFv6aUB9ug5dI5ESB/G81EULa4qfvQMA/19zw1nIDQZCxHTTVVl2Tj\r\n" + 
			"NwS0OpQmfr7iA5MY8UXQl9hXaFLzx3WchBg0BTpYRSm/ze46KeC81oQWoOeWFkOA\r\n" + 
			"Tu09U/nkZyCFQJ3F/B9iiB1PnHTMPHmlGgeXWaxpfHt4uGiSMwOvff2b4Ou6kb+M\r\n" + 
			"IGQWvPZ1PS9UXUNVTcR5SET90YXha5hwNUQ6F/TT7d5lMBILTLKrHKSLcaZXe57V\r\n" + 
			"HMDoRM5/ZnMmI17Duyyy7gZzQk812LJOzo1XMjID877WDnwISEKuYKbuDmwtoIU3\r\n" + 
			"hwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEogIBAAKCAQEAwK0jCF1j7fUiJO1XYgn69dBbPdl9PdgFv6aUB9ug5dI5ESB/\r\n" + 
			"G81EULa4qfvQMA/19zw1nIDQZCxHTTVVl2TjNwS0OpQmfr7iA5MY8UXQl9hXaFLz\r\n" + 
			"x3WchBg0BTpYRSm/ze46KeC81oQWoOeWFkOATu09U/nkZyCFQJ3F/B9iiB1PnHTM\r\n" + 
			"PHmlGgeXWaxpfHt4uGiSMwOvff2b4Ou6kb+MIGQWvPZ1PS9UXUNVTcR5SET90YXh\r\n" + 
			"a5hwNUQ6F/TT7d5lMBILTLKrHKSLcaZXe57VHMDoRM5/ZnMmI17Duyyy7gZzQk81\r\n" + 
			"2LJOzo1XMjID877WDnwISEKuYKbuDmwtoIU3hwIDAQABAoIBAQCY542asMqB5/xt\r\n" + 
			"w7CKDBOOCgiun6+Faa6+NgrZ5ZDdcaR6FAjrXk2tpCQOOdkwmyn+R1jgV1WJeZ4o\r\n" + 
			"BoBjQdwTKHeC2VlnUkJ4xb6QknAEYNqU3ue5TXulVLPLxk6QrJ4bZcfgvgn+TxrK\r\n" + 
			"HJ7+Ehl8CUeXIgQBV0yIR+z7tUhzbTbeEMF1k8OclC1A/3uG9v324AYSHQG1ybyY\r\n" + 
			"hcK9im9IM5uP96rLwvXkdrOCuMVLiXRx6sQ+7RUymR3UpYIH7W4fA9hPVen4CtXD\r\n" + 
			"xiaVa7tkFAq3Q/3zRBlOjWpQXB4hAPbWmKavGhZ0TtT46EQWOKfKuMETLv/Frv9u\r\n" + 
			"aAeftZOBAoGBAODVXiUMONQE0m9uR+ykabxKZVF15I++cgnRrLlcREfOScQso2eY\r\n" + 
			"JfxW+8dQy1VhxtU/sC35+bldnly6K7SPqUrxYXFv/KHdUSVkKjCEkMhD2qamoTlG\r\n" + 
			"3EO1qGpl49H2TlXCDdW3nvyt1eWUsAr/xHj6Wbc+C+H28fmxr4mjdulBAoGBANti\r\n" + 
			"nOtAWknO/j6SGsthfsiLQrhm/LfxxjjNtvuPx2PUZQiASqb4SYvPJc2WDtUYEhiV\r\n" + 
			"2u10KcQ3tqM2ICdrKlZeH5Nuznk1YQEu9iDLcUIwur/x/TAJPgQMkE+HeDVflSYG\r\n" + 
			"BjbCVWdjzhq29FXh2wnaEFn4GFC71dbgSnlupmbHAoGAXPofqrZ5McSkuYwjlksP\r\n" + 
			"ptwlNiDI2mDpjAH3qdfsLL20Z7oGASs8WjQmfvB8hdyfe0SRORItIWkDulkLBep7\r\n" + 
			"GhDkOSMUmGOL0SixActwjEDzRQOXnLYfFKkk8oscQZHYJ0LOsdjzmI7S69QnUQUg\r\n" + 
			"XN87BrpT86hJvPasHwFIhYECgYEAnucNe8Qv4ZghRVb0O23RtBZSbFiEIgPoLIqs\r\n" + 
			"SAe7R063I3IxIEFkm/SdkAxpj+WQbehVG3rxKaOlERCKN6MClm40dshimx6TtZS7\r\n" + 
			"R8OztFqxWYhAeEOpfKtQ95nK8vXZhSIF7DHURD/hMC5mSbJ+weMFxvTRP+DdoXbx\r\n" + 
			"2oTP2E8CfylxDF+5W++bpOGUKSWiUSSEd3DaFP2PybHDdhvjWxCG50y7WFz/PUNT\r\n" + 
			"OzBMouqvx6WVZf4zTw5Y1GiMLTX4vbB2P0lcrKVEGBQ7bMFqqEV98cJhx8+SmldO\r\n" + 
			"0XjCfqBGKnjZWFMjZjpTYkOK/uGzjYvW2GehQUMCLx6bwJsP+Bo=\r\n" + 
			"-----END RSA PRIVATE KEY-----";

}
