.8086
.MODEL SMALL
.STACK
.DATA
.CODE
START:
        MOV AL,00110111B
        MOV DX,226H
        OUT DX,AL
        MOV AX,1000H
        MOV DX,220H
        OUT DX,AL
        MOV AL,AH
        OUT DX,AL
        
        MOV AL,01110111B
        MOV DX,226H
        OUT DX,AL
        MOV AX,1000H
        MOV DX,222H
        OUT DX,AL
        MOV AL,AH
        OUT DX,AL
        
        MOV AL,10010111B
        MOV DX,226H
        OUT DX,AL
        MOV AL,05H
        MOV DX,224H
        OUT DX,AL
        JMP $
END START