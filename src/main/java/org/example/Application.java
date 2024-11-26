package org.example;

import org.example.Controllers.Controller;

import java.util.List;

public class Application {
  private final List<Controller> controllerList;

  public Application(List<Controller> controllerList) {
    this.controllerList = controllerList;
  }

  public void start() {
    for (Controller controller : controllerList) {
      controller.initializeEndpoints();
    }
  }
}
