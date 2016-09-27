package com.github.ldaniels528.rscaladsl

import org.scalatest.Matchers._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
  * "is" object tests
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
class IsTypeTests() extends FeatureSpec with GivenWhenThen with MockitoSugar {

  info("As an 'is' object")
  info("I want to be able test the type of a value")

  feature("Ability to test the type of a value") {
    scenario("Test the type of each value type") {
      Given("A Logical type")
      val b: RLogical = true

      When("the type is tested")
      val result = is.logical(TRUE)

      Then("the result should be true")
      result shouldBe RLogical(value = true)
    }
  }

}
