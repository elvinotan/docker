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
![Virtual Machine Vs Container](https://github.com/elvinotan/docker/blob/master/images/arsitektur.png)</br>
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
Ingat membuat container dari image bukan berarti langsung menjalankan container tersebut</br>
<b>docker container start {containername} : </b> Menjalankan container </br>
Secara default container akan membuka port 27017, tapi port ini adalah port si container bukan port host, artinya kita dari host tidak bisa mengakses ke port 27017
  
11. Menghapus Container  </br>
Untuk menghapus container syaratnya adalah container tersebut harus dalam status stop/inactive</br>
<b>docker container stop {containername1} {containername2} </b> : Stop container yang sedang active</br>
<b>docker container rm {containername1} {containername2} </b> : Hapus container 

12. Membukan port untuk Container</br>
<b>docker container create --name mongoserver1 -p 8080:27017 mongo:4.1</b> : Membuat container dgn nama mongoserver1 dan link port ke 8080</br>
--name {containername} : memberi nama container</br>
-p 8080:27017 : Expose port 27017 yang merupakan port container dan link ke 8080 yang merupakan port host</br>

13. Menghapus Image</br>
<b>docker image rm mongo:4.1</b> Menghapus image</br>
Syarat untuk menghapus image adalah, tidak boleah ada container yang me-reference ke image yang akan di hapus (running or not running)</br>
Karena setiap container running, container akan mereferensi ke image yg bersangkutan, artinya tetap terhubung</br>

14. Membuat image dengan Dockerfile</br>
PraSyartat : Butuh aplikasi yang akan dijadikan image, buat simple app yang berupa executeable jar</br>
Buat Dockerfile yang berfungsi sebagai build configuration file</br>
```
FROM openjdk:11.0.3-stretch

COPY docker.jar /app/docker.jar

CMD ["java", "-jar", "/app/docker.jar"]
```
<b>docker build --tag hello-world:1.0 . :</b> Membuat image dengan Dockerfile yang menjalankan docker.jar</br>
a. docker build : Perintah untuk membuat image</br>
b. --tag hello-world:1.0 : Memberi nama image hello-world dgn version 1.0</br>
Bila terjadi error "docker build" requires exactly 1 argument. tambahkan spasi . di belakang</br>

Karena ini hanya berupa client application bukan web application yg mengexpose port (always listening)
maka kita cukup menjalankan lewat image langsung ```docker run hello-world:1.0```
