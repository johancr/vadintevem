mvn -T 1C clean install -pl servlet,react -am -Dui=simple-ui && mvn appengine:update -pl appengine -Dversion=2
