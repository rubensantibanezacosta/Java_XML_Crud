import { BallService } from './services/ball.service';
import { Component, OnInit } from '@angular/core';
import { Ball } from './models/ball';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
balls:Ball[]=[];
ball:Ball=new Ball();
modalMode="";
index=0;
  constructor(private ballService:BallService){

  }
  ngOnInit(): void {
    this.getBalls();
  }

  getBalls(){
    this.ballService.getBalls().subscribe((data)=>{
      
      this.balls=data.balls;
      this.balls.shift();
    },
    (error)=>{console.error(error)})
  }


  newModal(){
    this.modalMode="Add new";
    this.ball=new Ball();
  }
  editModal(id:number, index:number){
    this.ball=this.balls[index];
    this.index=index;
    this.modalMode="Edit"
    this.ballService.getBallById(id).subscribe((data)=>{
      this.ball=data;
    },
    (error)=>{console.error(error)})
  }
  
    

  
  deleteBall(id:number, index:number){
    this.ballService.deleteBallById(id).subscribe((data)=>{
      console.log(data);
      data==1?this.balls.splice(index,1):null;
    },
    (error)=>{console.error(error)})
  }
  addBall(){

    if(this.modalMode=="Edit"){
    this.ballService.updateBall(this.ball,this.ball.id).subscribe((data)=>{
      if(data==1){
        this.balls.splice(this.index, 1, this.ball);
      }
    },
    (error)=>{console.error(error)})
  }else{
    this.ballService.addBall(this.ball).subscribe((data)=>{
      console.log(data);
      this.balls.push(<Ball>data);
    },
    (error)=>{console.error(error)}
    )
  }
  }

}
