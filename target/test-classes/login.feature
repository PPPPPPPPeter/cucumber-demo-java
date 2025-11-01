Feature: 用户登录功能
  作为一个网站用户
  我想要登录到系统
  以便我可以访问我的个人信息

  Scenario: 使用正确的凭证登录
    Given 用户在登录页面
    When 用户输入用户名 "testuser"
    And 用户输入密码 "password123"
    And 用户点击登录按钮
    Then 用户应该成功登录
    And 用户应该看到欢迎消息 "欢迎, testuser!"

  Scenario: 使用错误的密码登录
    Given 用户在登录页面
    When 用户输入用户名 "testuser"
    And 用户输入密码 "wrongpassword"
    And 用户点击登录按钮
    Then 用户应该看到错误消息 "用户名或密码错误"