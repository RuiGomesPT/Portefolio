from visual import *
from random import randrange

floor = box (pos=(0,0,0), length=80, height=0.2, width=80, color=(0,0.5,0.2))
towerBase = cylinder (pos=(0,1,38), axis=(0,3,0), color=(1,1,1))
tower = cylinder (pos=(0,0,38), axis=(0,1,0), radius=1.5, color=(1,0,0))
top = sphere(pos=(0,4,38), radius=1, color=(1,0,0))
cannon = cylinder (pos=(0,2.5,38), axis=(0,1,-3), radius=0.5, color=(1,1,1))
target = sphere(pos=(0, 4, 0), radius=2, color=(1,0,0))

repeat = true;
rate1 = 0.001;
grav = vector(0, -9.8, 0);
posVer = 0;
posHor = 0;
angleVer = 0;
posHor2 = 0;
deltat = 0.001;
massa = 0.1;
pot = -30;
targetList = [target];
score = 0;
tip = label( text="score: " + str(score), space=0.2, pos=(20,20), opacity=0.5);

while repeat == true:
    
    rate(1000);
    ev = scene.waitfor('keydown');


    def bombMove(bomb, posVor, posHor, pot):
        shoot = true;      
        if (posVer == 1):
            angleVer = 6;
        elif (posVer == 2):
            angleVer = 7;
        elif (posVer == 3):
            angleVer = 8;
        elif (posVer == -1):
            angleVer = 4;
        elif (posVer == -2):
            angleVer = 3;
        elif (posVer == -3):
            angleVer = 2;
        elif (posVer == 0):
            angleVer = 5;

        if (posHor == 1):
            angleHor = 0.0349066 * math.pi;
            posHor2 = 5;
        elif (posHor == 2):
            angleHor = 0.0698132 * math.pi;
            posHor2 = 10;
        elif (posHor == 3):
            angleHor = 0.10472 * math.pi;
            posHor2 = 15;
        elif (posHor == -1):
            angleHor = -0.0349066  * math.pi;
            posHor2 = -5;
        elif (posHor == -2):
            angleHor = -0.0698132 * math.pi;
            posHor2 = -10;
        elif (posHor == -3):
            angleHor = -0.10472 * math.pi;
            posHor2 = -15;
        elif (posHor == 0):
            angleHor = 0;
            posHor2 = 0;

        
        bomb.v = vector(posHor2, angleVer, pot * math.cos(angleHor));
        while shoot == true:
            rate(1000);
            net = massa * grav;
            bomb.v = bomb.v + (net/massa * deltat);
            bomb.pos = bomb.pos + bomb.v * deltat;
            angleVer = angleVer + 1;
            checkCol(bomb, target, tip);
            if bomb.pos.y <= 0.4:
                shoot = false;
                
    def checkCol(bomb, target, tip):
        global score
        if bomb.pos.z - bomb.radius < targetList[0].pos.z + targetList[0].radius:
            if bomb.pos.y> targetList[0].pos.y - targetList[0].radius and bomb.pos.y< targetList[0].pos.y + targetList[0].radius:
                if bomb.pos.x< targetList[0].pos.x + targetList[0].radius and bomb.pos.x> targetList[0].pos.x - targetList[0].radius:
                    bomb.pos.y = 0;
                    target.visible = false;
                    targetList.pop(0).visible = false;
                    bomb.visible = false;
                    tip.visible = false;
                    score = score + 1;
                    tip = label( text="score: " + str(score), space=0.2, pos=(20,20), opacity=0.5)
                    createNewTarget();

    def createNewTarget():
        ranX = randrange(-3, 4);
        numX = 0;
        if ranX == -3:
            numX = -16.5;
        elif ranX == -2:
            numX = -11;
        elif ranX == -1:
            numX = -5.5;
        elif ranX == 1:
            numX = 5.5;
        elif ranX == 2:
            numX = 11;
        elif ranX == 3:
            numX = 16.5;
        elif ranX == 0:
            numX = 0;

        ranY = randrange(2, 7);
        ranZ = randrange(-20, 20);
        colorR = math.fabs(ranX/4);
        colorG = math.fabs(ranY/4);
        colorB = math.fabs(ranZ/4);
        target = sphere(pos=(numX,ranY,3), radius=2, color=(colorR, colorG, colorB));
        targetList.append(target);

    if ev.event == 'keydown':
        if ev.key == 'a' and posHor > -3:
            cannon.rotate(angle=pi/24, axis=(0, 1, 0), origin=(0, 1, 38));
            posHor = posHor - 1;
            
        if ev.key == 'd'  and posHor < 3:
            cannon.rotate(angle=pi/24, axis=(0, -1, 0), origin=(0, 1, 38));
            posHor = posHor + 1;
            
        if ev.key == 'w' and posVer < 3:
            cannon.rotate(angle=pi/32, axis=(1, 0, 0), origin=(0, 1, 38));
            posVer = posVer + 1;
    
        if ev.key == 's' and posVer > -3:
            cannon.rotate(angle=pi/32, axis=(-1, 0, 0), origin=(0, 1, 38));
            posVer = posVer - 1;

        if ev.key == ' ':
            bomb = sphere(pos=(0,3,38), radius=0.2, color=color.red);
            bombMove(bomb, posVer, posHor, pot);

    
            
