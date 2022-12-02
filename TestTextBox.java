class TestTextBox extends TextBox {

  void testCompterOccurence() {
    assertEquals(5, compterOccurence("Il était une fois une princesse nomé Sarah qui avait le choix entre 3 voeux :\n   1- Etre belle\n   2- Etre riche\n   3- Les deux\n\nEt elle choisissa...", '\n'));
  }

  void testSeparerTexte() {
    String[] except = {"\n"};
    assertArrayEquals(except, separerTexte("\n", '\n', 1));
  }

}
