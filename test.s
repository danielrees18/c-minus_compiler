.text
	.align 4
.globl  doLoop
doLoop:
doLoop_bb2:
doLoop_bb3:
	movl	a(%RIP), %EDI
	movl	$0, %EAX
	cmpl	%EAX, %EDI
	jne	doLoop_bb1
doLoop_bb4:
doLoop_bb1:
	ret
