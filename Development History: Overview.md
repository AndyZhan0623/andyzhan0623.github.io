# Outline
The project can be roughly divided into three stages in chronological order. At the beginning of each new stage, I had a new set of goals and was dissatisfied with the existing project. Each time I moved on to the next stage, I created a new file and rewrote the entire project from scratch. As a result, I have at least three folders associated with the project, each corresponding to a different version of the project.

# Version 1: Console Program
After learning the basic syntax of Java, along with classes, loops, and conditions, I began trying to recreate Blackjack on my school computer. The initial version was very crude and rudimentary, with all of the game logic crammed into a single method and various nested loops and conditional statements. The only object used at runtime was ```PokerCard```.

<details>
  <summary>Click here to see the source code of V1</summary>
  ```
  public void blackjack() throws InterruptedException
	{
		X21 x1 = new X21();
		x1.chip = 100;
		int retry = 1;
		while(retry==1)
		{
			double chip0 = 100;
			retry=0;
			System.out.println("\n欢迎游玩21点！");
			System.out.println("每次开始游玩，您都将获得100的启动资金");
			x1.chip =100;
			int replay = 1;
			while(replay==1)
			{
				x1 = new X21();
				x1.chip = chip0;
				x1.cardsOut = new ArrayList<>();
				replay=0;
				System.out.print("请下注： ");
				x1.bet[0] = andySc2.nextDouble();
				while(x1.bet[0]>x1.chip||x1.bet[0]<=0)
				{
					System.out.print("\n请重新下注： ");
					x1.bet[0] = andySc2.nextDouble();
				}
				x1.chip-=x1.bet[0];
				
				Mfor21.preparing(x1); 	//Preparing Step: now 6 cards (2 null) on played;3
				
				x1.dSum = x1.z1.suiJiPaiValue + x1.z2.suiJiPaiValue;
				if(x1.dSum==21) x1.jackpotD=1;
				Mfor21.dHaveA(x1, x1.z1);
				Mfor21.dHaveA(x1, x1.z2);
				Mfor21.softenD(x1);
				
				x1.uSum[0]=x1.u1[0].suiJiPaiValue+x1.u2[0].suiJiPaiValue;
				if(x1.uSum[0]==21) x1.jackpotU=1;
				Mfor21.uHaveA(x1, x1.t, x1.u1[x1.t]);
				Mfor21.uHaveA(x1, x1.t, x1.u2[x1.t]);
				Mfor21.softenU(x1, x1.t);
				
				Mfor21.showOut(x1, x1.t);//Displaying the initial situation;
				
				if(x1.z1.paiMianRand==12&&x1.chip>=x1.bet[0]/2)
				{
					Thread.sleep(1000);
					System.out.print("\n是否购买保险？购买请按1： ");
					int insurance = andySc2.nextInt();
					if(insurance==1) Mfor21.insuranceM(x1);
				}
				
				int split = 0;
				int showZ2 = 0;
				if(x1.dSum!=21)
				{
					Thread.sleep(1000);
					if(x1.z1.paiMianRand==12) System.out.println("\n庄家并非21点");
					if(x1.u1[0].paiMianRand==x1.u2[0].paiMianRand&&x1.chip>=x1.bet[0])
					{
						Thread.sleep(1000);
						System.out.print("\n是否分牌？分牌请按1： ");
						split = andySc2.nextInt();
						if(split==1)
						{
							Mfor21.splitM(x1);
							x1.chip-=x1.bet[0];
							x1.bet[1]=x1.bet[0];
							x1.t=1;
						}
					}
						
					
					for(int i=0;i<=x1.t;i++)
					{	
						x1.uSum[i] = x1.u1[i].suiJiPaiValue + x1.u2[i].suiJiPaiValue;
						
						if(split==1)
						{
							System.out.print("\n第"+(i+1)+"局： ");
							Mfor21.showOut(x1,i);
						}
						
						int doubleDown = 0;
						if(x1.chip>=x1.bet[i])
						{
							Thread.sleep(1000);
							System.out.print("\n是否双倍下注？双倍下注请按2： ");
							doubleDown = andySc2.nextInt();
							if(doubleDown==2)
							{
								x1.chip-=x1.bet[i];
								x1.bet[i]*=2;
								x1.h = new SuiJiPai();
								Mfor21.hitU(x1,i);
								Mfor21.showUHit(x1, i, x1.h);
							}
						}
						
						if(doubleDown!=2)
						{
							System.out.print("\n是否加牌？加牌请按1： ");
							int toHit = andySc2.nextInt();
							while(toHit==1)
							{
								x1.h = new SuiJiPai();
								Mfor21.hitU(x1,i);
								Mfor21.showUHit(x1,i,x1.h);
								if(x1.uBust[i]==1) toHit = 0;
								else toHit = andySc2.nextInt();														
							}
						}
						
						if(x1.uBust[i]!=1)
						{
							showZ2 = 1;
						}
						//before this line are all in the for loop;
					}	
					//before this line, all stuff run on the condition that dSum!=21;						
				}
				else
				{
					showZ2 = 1;
				}
				
				
				
				if(showZ2==1)
				{
					Thread.sleep(1000);
					System.out.print("\n总和："+x1.dSum+"--"+x1.z2.suiJiPai);
					while(x1.dSum<17&&x1.jackpotU!=1)
					{
						x1.h = new SuiJiPai();
						Mfor21.hitD(x1);
						Mfor21.showDHit(x1, x1.h);
					}
				}
				
				
				
				//The result is putting out;
				for(int i=0;i<=x1.t;i++)
				{
					if(split==1)
					{
						Thread.sleep(1000);
						System.out.print("\n\n第"+(i+1)+"局： ");
					}
					Mfor21.resulting(x1, i);
					Mfor21.showResult(x1,i);
				}
				
				System.out.print("\n\n当前筹码： "+x1.chip);
				
				if(x1.chip==0)
				{
					replay=0;
					Thread.sleep(1000);
					System.out.print("\n你输光了钱");
				}
				else
				{
					chip0 = x1.chip;
					System.out.print("\n是否继续游戏？继续请按1: ");
					replay = andySc2.nextInt();
				}
			}
			Thread.sleep(1000);
			System.out.print("\n是否重新开始？重新开始请按1： ");
			retry = andySc2.nextInt();
		}
		System.out.print("\n你的余额是： "+x1.chip);
		andySc2.close();
	}
</details>

# Version 2: Front-end UI

Dissatisfied with the rather rudimentary input and output capabilities of Java's console, I saw two possible ways to implement a graphical interface: using Java APIs such as Swing and JavaFX, or integrating a web interface with HTML, CSS, and JavaScript for front-end interaction. Since I already had a basic understanding of HTML, CSS, and JavaScript at the time, and a local application was not ambitious enough for me, I decided to switch to IntelliJ IDEA and learn Spring Boot. (The version of Eclipse on my school computer did not have the Maven plugin, making Spring Boot configuration difficult.)

After learning the basics of the request-response model and getting more familiar with HTML, CSS, and JavaScript, I built a second, much more substantial version of the game that could be played through a web browser, although it was still hosted locally. While the game logic was still somewhat rudimentary, I had introduced several new runtime objects: ```BlackjackPlayer```, ```BlackjackDealer```,  and ```BlackjackDeck```. At the very least, this allowed me to encapsulate some of the game's data fields.

# Version 3: Database & JWT Authentication

To allow players to retain their balances instead of having to start over every time, I began learning SQL and working with MySQL. As modern REST APIs tend to be stateless, I decided to design the server to be stateless as well. When implementing user authentication, I also took the opportunity to learn how to configure JWT and how its authentication mechanism works.

After building a simple addition simulator to practice these two new technologies, I integrated them into the project and began developing the third major version—this unfinished version available on GitHub.

Beyond introducing new technologies, I almost completely overrode my previous approach to code organization. As I wrote the new version, I carefully considered and analyzed whether responsibilities were properly separated, whether the architecture was extensible, and whether the design would accommodate future changes. Through this day-to-day process of reviewing, refining, and rethinking my decisions, I gradually developed a much deeper understanding of object-oriented programming (OOP), interfaces, generics, and other fundamental programming concepts.
