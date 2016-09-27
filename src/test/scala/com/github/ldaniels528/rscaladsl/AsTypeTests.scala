package com.github.ldaniels528.rscaladsl

import org.scalatest.Matchers._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
  * "as" object tests
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
class AsTypeTests() extends FeatureSpec with GivenWhenThen with MockitoSugar {

  info("As an 'as' object")
  info("I want to be able convert values from one type to another")

  feature("Ability to convert values from one type to another") {
    scenario("Convert a value from string to double") {
      Given("A string value")
      val s: RString = "1234.56"

      When("the value is converted")
      val result = as.double(s)

      Then("the result should be true")
      result shouldBe RDouble(1234.56)
    }

    scenario("Convert a value from string to integer") {
      Given("A string value")
      val s: RString = "123456"

      When("the value is converted")
      val result = as.integer(s)

      Then("the result should be true")
      result shouldBe RInteger(123456)
    }
  }

}
