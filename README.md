# AcceraTeste
Teste da empresa Accera leitor de código QR para consumo de uma API de Star Wars

Desenvolvido por: Matheus Artur Saviczki Santana

Linkedin: https://www.linkedin.com/in/matheus-saviczki-santana-489790132/

Stackoverflow: https://pt.stackoverflow.com/users/87323/matheus

Portfólio: https://sites.google.com/site/matheusprogrammer94/

*********************************************************************************

Libs e Frameworks Utilizados:
 -Consumo de Webservice: Retrofit - com.squareup.retrofit2:retrofit:2.1.0
 
 -Deserializador JSON: GSON - com.squareup.retrofit2:converter-gson:2.0.0-beta4
 
 -Banco de Dados Local: Realm - io.realm:realm-android:0.84.1
 
 -ImageView Circular: CircularImageView - de.hdodenhof:circleimageview:2.1.0
 
 -Leitor de Código QR: ZXIngReader - me.dm7.barcodescanner:zxing:1.9
 
 -Padrão MVP
 
 *********************************************************************************
 
 Funcionamento:
 -Ao abrir o aplicativo pela primeira vez, o mesmo irá pedir permissão de GPS, caso
 o usuário não de a permissão, quando for fazer verificação nos detalhes não irá
 aparecer sua localização, apenas a palavra 'Unknown' seguido do horário local.
 
 -O menu possui duas TabPage, uma onde o usuário irá ver a lista de códigos QR
 capturados que possui o nome de 'LISTA' e outra onde ele irá utilizar para
 capturar os códigos QR com o nome de 'CAPTURAR'.
 
 -Ao clicar na aba capturar o usuário irá se deparar com uma foto do Darth Vader
 clicando em cima da foto na primeira vez, o App irá fazer uma solicitação
 de permissão de Camera, onde só poderá ser utilizado o Scanner caso o usuário
 permita o acesso a camera.
 
 -Para capturar um código QR, mire em direção do mesmo com o 'Fetch View' centrealizado
 na View, caso o resultado sejá 'https://swapi.co/api/people/1/' ele irá fazer a busca
 das informações do personagem na API, e após fazer essa busca, o mesmo é Salvo no banco
 de dados local e listado na aba 'LISTA'. Obs: A busca na API só pode ser utilizada caso
 aja conexão com a internet, do contrário irá apenas aparecer um Toast com o resultado do código
 QR lido e uma mensagem avisando que não a conexão com a internet.
 
-Támbem foi utilizado a API 'https://api.qwant.com/api/search/' para busca de imagem dos personagens
esta busca é utilizada após pegar informações do pernagem, onde a busca é feita através do nome
obtido através da API 'swapi'.

-Ao clicarem um dos itens da lista, irá exibir uma tela com uma imagem do personagem
e as informações trazidas da API 'swapi' sobre o personagem, a localização
e o horário que foi realizado o scan do código QR. Tambem irá mostrar um
'ImagePreview' dos filmes em que o personagem listado já teve participação. Clicando
em algum dos filmes irá exibir o nome do filme e um Postêr do mesmo.

-O usuário poderá ver as informações dos personagens já capturados mesmo sem conexão com a Internet.

 *********************************************************************************
 
 
 
 
