# MICROSERVICES EXAMPLE

## USE OF DOCKER COMPOSE

> To use docker compose follow the next steps:
> - First, open **docker** folder,
> - Then, copy ***.env_template*** file, and rename it to ***.env***,
> - Finally, execute `docker compose up -d` command

## DEVELOPMENT ENVIROMENT

# Install PostgreSQL with Docker

To run PostgreSQL in a Docker container with persistent data storage, follow these steps:

## 1. Create Docker Volumes

First, create named volumes to persist your PostgreSQL data and configuration:

```bash
docker volume create postgres_data
docker volume create postgres_config
```

## 2. Run PostgreSQL Container

Run the following command to start a PostgreSQL container with persistent storage:

```bash
docker run --name postgres-db \
  -e POSTGRES_USER=your_username \
  -e POSTGRES_PASSWORD=your_secure_password \
  -e POSTGRES_DB=your_database_name \
  -p 5432:5432 \
  -v postgres_data:/var/lib/postgresql/data \
  -d postgres:12.15
```

## 3. Verify the Container

Check if the container is running:

```bash
docker ps
```

## 4. Connect to PostgreSQL

You can connect to your PostgreSQL instance using:

```bash
docker exec -it postgres-db psql -U your_username -d your_database_name
```

## 5. Connect Using DBeaver

To connect to your PostgreSQL database using DBeaver:

1. **Open DBeaver** and click on the "New Database Connection" button (or press `Ctrl+`).

2. In the database selection window, search for and select **PostgreSQL**, then click "Next".

3. **Connection Settings**:
   - **Host**: `localhost` (or your Docker host IP if connecting remotely)
   - **Port**: `5432` (default PostgreSQL port)
   - **Database**: `your_database_name` (as specified in your Docker run command)
   - **Username**: `your_username` (as specified in your Docker run command)
   - **Password**: `your_secure_password` (as specified in your Docker run command)

4. **Test Connection**:
   - Click "Test Connection" to verify everything is working correctly
   - You should see a "Connected" message if successful

5. **Save Connection**:
   - Give your connection a meaningful name (e.g., "Docker PostgreSQL - YourDB")
   - Click "Finish" to save the connection

6. **Troubleshooting**:
   - If you can't connect, ensure your PostgreSQL container is running (`docker ps`)
   - Check that port 5432 is not blocked by a firewall
   - Verify your credentials match those used when starting the container
   - If using Docker Toolbox or Docker MaFchine, use the machine's IP instead of localhost

## 6. Using Docker Compose (Recommended)

Alternatively, you can use the following `docker-compose.yml` configuration:

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:latest
    container_name: postgres-db
    environment:
      POSTGRES_USER: your_username
      POSTGRES_PASSWORD: your_secure_password
      POSTGRES_DB: your_database_name
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - postgres_config:/etc/postgresql
    restart: unless-stopped

volumes:
  postgres_data:
  postgres_config:
```

Save this in your `docker-compose.yml` and run:

```bash
docker-compose up -d
```

## Important Notes

1. Replace `your_username`, `your_secure_password`, and `your_database_name` with your desired credentials.
2. The data will persist even if the container is stopped or removed.
3. The default port 5432 is exposed to the host machine. Change the first port number if 5432 is already in use.
4. For production use, consider using more secure password management and additional security measures.