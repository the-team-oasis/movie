# Copy the default node container
FROM node:10
 
# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
 
# Install app dependencies
# COPY package.json /usr/src/app
# COPY Gruntfile.js /usr/src/app
 
# Bundle app source
COPY . /usr/src/app

RUN npm install
RUN npm install -g @oracle/ojet-cli@6.1.0
RUN npm install @oracle/oraclejet-tooling@6.1.0 --save

# Expose the application port
EXPOSE 8000
 
# Run grunt serve inside the container to start the JET app
CMD ["ojet", "serve"]