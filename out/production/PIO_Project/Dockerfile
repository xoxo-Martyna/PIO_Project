FROM openjdk:14
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac /usr/src/myapp/tests/Test.java
CMD ["java", "tests.Test"]
