.text
	.align 4
.globl  test
test:
test_bb1:
test_bb2:
	movl	$0, %EDI
	movl	$1, %EDI
	movl	$0, %EDI
	cmpl	%EDI, %ESI
	jle	test_bb5
test_bb3:
	movl	$1, %EAX
	subl	%EAX, %EDI
test_bb4:
	cmpl	%EDI, %ESI
	jne	test_bb14
test_bb13:
	cmpl	%EDI, %ESI
	jne	test_bb14
test_bb15:
	cmpl	%EDI, %ESI
	jne	test_bb18
test_bb17:
	movl	$1, %EAX
	movl	%EDI, %ESI
	subl	%EAX, %ESI
	cmpl	$0, %ECX
	jne	test_bb17
test_bb18:
	cmpl	$0, %R8D
	jne	test_bb15
test_bb14:
	movl	$3, %EAX
	movl	%EDX, %EAX
	ret
test_bb10:
	movl	$1, %EDI
	movl	$1, %R9D
	cmpl	%R9D, %EDI
	jne	test_bb9
test_bb11:
	movl	$2, %R9D
	addl	%R9D, %EDI
	cmpl	$0, %R10D
	jne	test_bb11
test_bb12:
	jmp	test_bb9
test_bb5:
	movl	$2, %EDI
	movl	$2, %R9D
	cmpl	%R9D, %EDI
	jne	test_bb4
test_bb6:
	movl	$2, %EDI
	movl	$2, %R9D
	cmpl	%R9D, %EDI
	jne	test_bb10
test_bb8:
	movl	$1, %R9D
	addl	%R9D, %EDI
test_bb9:
	cmpl	$0, %EAX
	jne	test_bb6
test_bb7:
	jmp	test_bb4
