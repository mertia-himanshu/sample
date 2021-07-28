package org.tmt.sample.core

import org.tmt.sample.core.models.{AdminGreetResponse, GreetResponse, UserInfo}

import scala.concurrent.Future

class SampleImpl() {
  def greeting(userInfo: UserInfo): Future[GreetResponse] = Future.successful(GreetResponse(userInfo))

  def adminGreeting(userInfo: UserInfo): Future[AdminGreetResponse] =
    Future.successful(AdminGreetResponse(userInfo))
}
