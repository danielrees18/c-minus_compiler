int z;

int doAddition(void) {
  int k;
  int i;
  i = 4;
  z = i + 2;
  k = z + getValue(9);
  return k;
} 

int getValue(int x) {
  return x;
}