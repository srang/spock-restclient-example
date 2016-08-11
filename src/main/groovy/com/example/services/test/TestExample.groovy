package com.example.services.test

import groovyx.net.http.RESTClient
import spock.lang.*
import static groovyx.net.http.ContentType.*

class TestExample extends Specification {

    @Shared
    def client = new RESTClient("http://jsonplaceholder.typicode.com/")

    def "Simple status checker"() {
        when: "a rest call is performed to the status page"
        def response = client.get(path : "posts")

        then: "the correct message is expected"
        with(response) {
            status: 200
        }
    }

    def "Cleaning all products"() {
        when: "a product list is requested"
        def response = client.delete(path : "posts/1")


        then: "it should be empty"
        with(response) {
            status: 200
        }
    }

    def "Creating a product"() {
        given: "a json file"
        def inputFile = new File("src/main/resources/test.json").getText()
        when: "a new product is created"
        def response = client.post(
                requestContentType: JSON,
                path : "posts",
                body :  inputFile
        )

        then: "it should have default values"
        with(response) {
            status == 201
            data.id == 101
        }

    }


}