<h3 align="center">Hotel-Reservation-System</h3>

  <p align="center">
    A simple reservation system to help hotel managers manage their business!
    <br />
  </p>

## Installation (Using Docker)

Use the following steps to set up the project repository. Run all of these commands inside the project folder in WSL.

1. Make sure to install <a href="https://learn.microsoft.com/en-us/windows/wsl/install">**WSL**</a> and <a href="https://www.docker.com/">**Docker Desktop**</a>.
2. Clone the project repository into the **/etc/home/[username]** directory after you open up wsl. (You can also just use the local repository you've been using on windows this whole time.)
3. To make it easier you can run vscode in that repository by opening WSL and running the command ```code .```
4. Open up docker desktop and make sure that these are enabled. <p><img src="https://i.ibb.co/ckJpCtf/Docker-Desktop-hi-Dg-Tax0-WD.png"/></p> <img src="https://i.ibb.co/9GmZT73/Docker-Desktop-k-BB1ud-Hh-W6.png"/>
5. Run the following command to install the composer dependencies. 
``` bash
docker compose up -d
```
Congrats that all the steps to get it running :D
<p><img src="https://i.imgur.com/gzg6ffg.png"/></p>


## Installation (No Docker)

### Clone the repo
```sh
git clone https://github.com/sergiumereacre/hotelreservation.git
```
### Install this specific version of Java and follow the steps for Maven.
- Use this link to download [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- Use this [tutorial](https://phoenixnap.com/kb/install-maven-windows) to install Maven on windows.
- USe this [Node.JS](https://nodejs.org/en/download/current) to download and install Node.js.

### Once Maven is setup and you also have Node.js installed.
This command will install all the dependencies for the backend and the front end.
```sh	
npm install
```

If you want to run the install for frontend or backend independently, you can use the following commands.
```sh
npm run frontend:install
npm run backend:install
```

### Once the dependencies are installed you dont have to run those commands again only when you want to update some packages.
Open up 2 terminals and run the following commands in each terminal.
```sh
npm start frontend
npm start backend
```


## Contributors
1. Sergiu Mereacre
2. Jack O'Brien
3. Edison Cai
4. David Walsh


## License
Distributed under the MIT License. See `LICENSE.txt` for more information.