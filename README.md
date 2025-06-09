<a id="readme-top"></a>


<h3 align="center">Tree Traversal</h3>

 

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>

## About The Project

The main focus of this project is the `/get_unique` endpoint in the TreeWalker (port 8080) Spring Boot application. You send a GET request to the endpoint with a directory path as a request parameter, and it will return the list of file names in that directory—filtered to be unique.

Each request is logged in a PostgreSQL database: it stores the username, request timestamp, input path, and the list of unique files returned.

The History service (port 8081) provides a `/history` endpoint to access those logs in detail.

Both services are containerized using Podman, orchestrated via a single Makefile.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

[![Java](https://img.shields.io/badge/Java-21-blue?style=flat-square\&logo=openjdk)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=flat-square\&logo=spring)](https://spring.io/projects/spring-boot)
[![Swagger UI](https://img.shields.io/badge/SwaggerUI-2.5.0-orange?style=flat-square\&logo=swagger)](https://swagger.io/tools/swagger-ui/)
[![Podman](https://img.shields.io/badge/Podman-container-lightgrey?style=flat-square\&logo=podman)](https://podman.io/)
[![Makefile](https://img.shields.io/badge/Makefile-Automation-yellow?style=flat-square)](https://www.gnu.org/software/make/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

To get a local copy up and running, follow these steps.

### Prerequisites

* Linux
* Podman
* Make

### Installation

Install Make:

```bash
sudo apt install make       # Debian/Ubuntu
sudo dnf install make       # Fedora
sudo pacman -S make         # Arch
```

Install Podman:

```bash
sudo apt install podman       # Debian/Ubuntu
sudo dnf install podman       # Fedora
sudo pacman -S podman         # Arch
```

Clone the repository:

```bash
git clone https://github.com/Tavirutyutyu/TreeTraversal.git
cd TreeTraversal
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage

Start the project:

```bash
make build-and-run
```

Then open your browser and navigate to:

* [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) — for `/get_unique`
* [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) — for `/history`

To stop all services:

```bash
make stop
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
