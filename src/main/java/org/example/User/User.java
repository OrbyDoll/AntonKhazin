package org.example.User;

/**
 * Представляет пользователя с именем и фамилией.
 * <p>
 * Объект {@code User} хранит имя и фамилию пользователя, предоставляя доступ
 * к этим данным через соответствующие методы.
 * </p>
 */
public class User {

  /**
   * Имя пользователя.
   */
  private final String firstName;

  /**
   * Фамилия пользователя.
   */
  private final String lastName;

  /**
   * Создает объект {@code User} с указанным именем и фамилией.
   *
   * @param firstName  имя пользователя
   * @param lastName   фамилия пользователя
   */
  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Возвращает имя пользователя.
   *
   * @return имя пользователя
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Возвращает фамилию пользователя.
   *
   * @return фамилия пользователя
   */
  public String getLastName() {
    return lastName;
  }
}
