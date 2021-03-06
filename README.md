# Docker
01. Pengenalan Docker
Old way    : Meng-Setup Secara manual di server
			Install Db, Install JVM, install App	
Docker way : Membuat image dan membuat container
			Buat image untuk masing masing lalu buat container 
			
02. Virtual Machine Vs Continer
Virtual Mesin : memiliki OS masing-masing dan tidak terhubung dgn host mesin
Container : Hanya berupa library yg hidup di current host os (Smaller, Faster dan redeployable, terisolasi)
![Virtual Machine Vs Container](https://github.com/elvinotan/docker/blob/master/images/containers-vs-virtual-machines.jpg)

03. MengInstall Docker
https://docs.docker.com
![Virtual Machine Vs Container](https://github.com/elvinotan/docker/blob/master/images/logo.png)
</br>Cek Instalasi</br>
<b>docker info</b> Docker engine berbasiskan Linux</br>
<b>docker version</b>

04. Arsitektur Docker</br>
![Arsitektur Docker](https://github.com/elvinotan/docker/blob/master/images/arsitektur.png)</br>
Client : Command > Kirim command ke server</br>
Server : Images and Containers</br>
Registry = Images Host</br>

06. Container Registry</br>
Tempat penyimpanan images (shareable, reuseable)</br>
Layaknya images store</br>
a. Docker Hub : https://hub.docker.com/</br>
b. Google Container Registry : https://cloud.google.com/container-registry/</br>
c. AWS Elastic Container Registry : https://aws.amazon.com/id/ecr/</br>

07. Images</br>
Image - Container - Ready</br>
DokcerHub Menyediakan image-image, yang sudah siap di gunakan </br>
Tag : Image Version</br>

08. Container</br>
Container adalah image yang kita jalankan, yang menjadi instance</br>
Bisa menjalankan multipe container yang berasal dari 1 image </br>

09. Mengambil image dari dockerhub</br>
<b>docker images :</b> Menampilkan daftar image yang ada </br>
a. REPOSITORY : Nama images</br>
b. TAG : Image Version</br>
c. IMAGE_ID : Id Image</br>
d. CREATED : Tanggal pembuatan </br>
e. SIZE : Ukuran</br>
<b>docker pull {imagename} :</b> Mengambil image dari images Registry</br>
<b>docker pull mongo : </b> Mengambil image dari images Registry latest images</br>
<b>docker pull mongo:{tag} : </b> Mengambil image dari images Registry pada versi tertentu</br>

10. Membuat Container</br>
<b>docker container ls : </b> Untuk melihat daftar container yang running</br>
<b>docker container ls --all : </b> Untuk melihat daftar container yang running maupun yang tidak</br>
a. CONTAINER_ID : Id dari continer</br>
b. IMAGE : Container ini berasal dari image mana</br>
c. COMMAND : Perintah menjalankan container ini (runnable entry point)</br>
d. CREATED : Waktu di buatnya container ini</br>
e. STATUS : Status continer</br>
f. PORT : Port yang digunakan oleh container ini yang kita bisa access</br>
g. NAMES : Nama Continer</br>
<b>docker container create mongo:4.1 :</b> Membuat container dgn randomname</br>
<b>docker container create --name mongoserver1 mongo:4.1 :</b> Membuat container dgn name mongoserver1, nama container bersifat uniqe, begitu di jalankan akan menghasilkan container id.</br>

11. Menjalankan Continer</br>
Ingat membuat container dari image bukan berarti langsung menjalankan container tersebut</br>
<b>docker container start {containername} : </b> Menjalankan container </br>
Secara default container akan membuka port 27017, tapi port ini adalah port si container bukan port host, artinya kita dari host tidak bisa mengakses ke port 27017
  
12. Menghapus Container  </br>
Untuk menghapus container syaratnya adalah container tersebut harus dalam status stop/inactive</br>
<b>docker container stop {containername1} {containername2} </b> : Stop container yang sedang active</br>
<b>docker container rm {containername1} {containername2} </b> : Hapus container 

13. Membukan port untuk Container</br>
<b>docker container create --name mongoserver1 -p 8080:27017 mongo:4.1</b> : Membuat container dgn nama mongoserver1 dan link port ke 8080</br>
--name {containername} : memberi nama container</br>
-p 8080:27017 : Expose port 27017 yang merupakan port container dan link ke 8080 yang merupakan port host</br>

14. Menghapus Image</br>
<b>docker image rm mongo:4.1</b> Menghapus image</br>
Syarat untuk menghapus image adalah, tidak boleah ada container yang me-reference ke image yang akan di hapus (running or not running)</br>
Karena setiap container running, container akan mereferensi ke image yg bersangkutan, artinya tetap terhubung</br>

15. Membuat image dengan Dockerfile</br>
PraSyartat : Butuh aplikasi yang akan dijadikan image, buat simple app yang berupa executeable jar</br>
Buat Dockerfile yang berfungsi sebagai build configuration file</br>
```
FROM openjdk:11.0.3-stretch

COPY docker.jar /app/docker.jar

CMD ["java", "-jar", "/app/docker.jar"]
```
</br>
<b>docker build --tag hello-world:1.0 . :</b> Membuat image dengan Dockerfile yang menjalankan docker.jar</br>
a. docker build : Perintah untuk membuat image</br>
b. --tag hello-world:1.0 : Memberi nama image hello-world dgn version 1.0</br>
Bila terjadi error "docker build" requires exactly 1 argument. tambahkan spasi . di belakang</br>

Karena ini hanya berupa client application bukan web application yg mengexpose port (always listening)
maka kita cukup menjalankan lewat image langsung ```docker run hello-world:1.0```

16. Mengupload Image ke Registry</br>
Kita akan mencoba untuk mengupdate image ke Docker hub, tetapi karena repository kita besifat pribadi maka alamat repository kita akan memiliki signature berupa {username}/image:tag</br>
Oleh sebab itu kita harus membuat current image dgn aturan tersebut</br>
<b>docker tag hello-world:1.0 elvinotan/hello-world:1.0 </b> Membuat duplicate image dgn image name tertentu</br>
<b>docker login </b> Login ke docker hub</br>
<b>docker push elvinotan/hello-world:1.0</b> Push image ke docker hub repository</br>

17. Environment Variabel di Docker</br>
Pada topik ini kita akan mencoba untuk meng-inject Enviroment variable ke spring app</br>
File application.properties</br>
app.name.creator=${NAME}</br>
app.name.createdDate=${TGLBUAT}</br>
Kita perlu meng-inject nilai ${NAME} pada saat pembuatan continer</p>
misal nama imagenya adalah javadocker</br>
<b>docker container create --name javaDockerApp -p 8080:8080 -e NAME=Docker -e TGLBUAT=20190101 javadocker:1.0</b>: Membuat container dengan meng-inject enviroment variable</br>
<b>docker container inspect javaDockerApp</b> Untuk mengecek apakah Enviroment kita sudah terdaftar kita bisa menjalankan perintah, lalu cari bagian ENV</br>
<b>docker container logs javaDockerApp</b> Untuk melihat log aplikasi </br>

18. Integrasi Container dengan Network</br>
Pada topik ini kita akan belajar bagaimana menghubungan suatu continer dengan container lainnya melalu port</br>
a. Continer mogo, dengan open port 27017</br>
b. Continer redis, dengan open port 6379</br>
c. Continer java-docker. dengan open port 8080</p>
Apa yang salah dgn statement di bawah ini </br>
<b>docker container create --name java-docker -p 8080:8080 -e NAME=Docker -e MONGO_HOST=localhost -e MONGO_PORT=27017 -e REDIS_HOST=localhost -e REDIS_PORT=6379 java-dockre:1.0</b></br>
java-docker mengarah mongo dan redis lewat host localhost, ingat ini berupa container loh, definisi localhost bagi java-docker adalah si container java-docker itu sendiri</p>
Solusinya adalah dengan mengarahkan host ke masing masing continer name</br>
<b>docker container create --name java-docker -p 8080:8080 -e NAME=Docker -e MONGO_HOST=mongo -e MONGO_PORT=27017 -e REDIS_HOST=redis -e REDIS_PORT=6379 java-dockre:1.0</b></p>
Masih tetap error karena continer java-docker tidak tau apa itu hostname mongo dan hostname redis. Ia tidak ngerti kalo hostaname mongo adalah container mongo</br>
Agar mereka saling mengerti kita harus bikin ke 3 container ini dalam 1 network yang sama</p>
<b>docker network create java_network</b> : Membuat docker network</br>
<b>docker network ls</b> : Menampilkan network yang sudah di buat</p>
Masukan masing masing continer ke network java-net</br>
<b>docker network connect {networname} {continername}</b></br>
bila ingin menghapus relasi container dan network gunakan disconnect</p>
Untuk melihat container terdaftar di network mana jalankan</br>
<b>docker constiner inspect java-docker</b> lihat bagian network</br>
Restart java-docker, sudah bia connect</br>

19. Menggunakan Docker Compose</br>
Kalo kita lihat tahap pada no 18 sangat time consuming, mulai dari download image, create container, buat network serta menjalankan continer.</br>
Untuk mempermudah ini kita akan buat Docker compose yang akan memadukan semua perintah di atas menjadi 1 file</br>
buat file docker-compose.yml
```
version: "3.7"

services:
  mongo:
  	container_name: mongo
  	image: mongo:4-xenial
  	ports:
  	  -	27017:27017
  	networks:
  	  - java_network  
  redis:
    container_name: redis
    image: redis:5
    ports:
      - 6379:6379
  	networks:
  	  - java_network        
  java-docker:
    container_name: java-docker
    image: java-docker:1.0
    ports:
      -8080:8080
   	networks:
  	  - java_network       
    depends_on:
      - redis
      -  mogo
    environment:   
      - NAME=Docker
      - MONGO_HOST=mongo
      - MONGO_PORT=27017
      - REDIS_HOST=redis
      - REDIS_PORT=6379
networks:
  java_network:
    name: java_network 
```
```
version : Versi docker-compose, gunakan versi terbaru
services : list of image and container yang akan kita gunakan
  container_name : Nama Container
  image : Nama image 
  ports : port yang akan di expose {host port: container port}
  network : nama network yang akan continer ini daftarkan
  depends_on : list of continer yang harus hidup dahulu sebelum current container
  enviroment : list of enviroment yang di butuhkan oleh container
network : list of network yang di butuhkan oleh continar 
```
Untuk menjalankan docker-compose harus berada di folder yang mengandung docker-compose.yml atau docker-compose.yaml</br>
<b>docker-compose up</b> Membuat semua container bila blm ada dan otomatis menghidupkannya</br>
<b>docker-compose down</b> Stop semua container dan menghapusnya HATI-HATI ingat ini menghapus continer, bila ada db yang masuk maka akan terhapus juga</br>
<b>docker-compose stop</b> Stop semua container </br>
<b>docker-compose start</b> Start semua container </br>

20. Manage Data di Docker</br>
Docker menyarankan agar container itu bersifat stateless</br>
Tp bila kita ingin menyimpan data pada mongo misalnya maka kita harus menggunakan storage</br>
Ingat bila kita buat continer mogo, insert data, lalu hapus continernya maka data juga akan terhapus</br>
<b>docker volume create mongo_data</b> Membuat volume</br>
<b>docker container create --name mongo -p 27017:27017 -v mongo_data:/data/db mongo:4-xenial</b> Melakukan mapping /data/db ke volume mongo_data. Sehingga data yang di insert akan masuk ke volume</p>

Selain Volume ada mekanisme lain yang bisa di pakai, tetapi volume ini memiliki keunggulan antara lain Backup, Restore, Migrate</br>
Tapi kekurangannya adalah kita tidak memiliki access terhadap volume tersebut, volume sgt baik untuk production tapi kurang flexible untuk development</br>
Untuk itu kita akan coba juga dgn cara bind mount yaitu mounting folder</br>
<b>docker container create --name mongo -p 27017:27017 -v /home/elvino/mogodb:/data/db mongo:4-xenial</b> Melakukan mapping /data/db ke folder /home/elvino/mongodb. Sehingga data yang di insert akan masuk ke folder tersebut</br>


