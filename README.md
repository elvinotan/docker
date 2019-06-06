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
<b>docker info</b> Berbasiskan Linux</br>
<b>docker version</b>

04. Arsitektur Docker</br>
![Virtual Machine Vs Container](https://github.com/elvinotan/docker/blob/master/images/arsitektur.png)</br>
Client : Command > Kirim command ke server</br>
Server : Images and Containers</br>
Registry = Images Host</br>

06. Container Registry
Tempat penyimpanan images (shareable, reuseable)</br>
Layaknya images store</br>
a. Docker Hub : https://hub.docker.com/</br>
b. Google Container Registry : https://cloud.google.com/container-registry/</br>
c. AWS Elastic Container Registry : https://aws.amazon.com/id/ecr/</br>


