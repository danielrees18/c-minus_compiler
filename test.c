/* testcode.c */

int a;

int addThem(int d, int e) {
  int f;
  f = d + e;

  return f;
}

int main (void) {

  int b;
  int c;
  int g;
  int h;
  int i;

  b = c = 5;

  if (b == 5) {
    a = a + 3;
  }
  else {
    a = 4;
  }

  g = 0;
  i = 1;
  while (i <= 8) {
    g = g + i;
    i = i+1;
  }
  h = g / 3;
  g = h * 4;


  c = addThem(a, b);
  putchar (56);
  putchar (61);
  putchar (g+c);
  putchar (10);

  return 0;
}

