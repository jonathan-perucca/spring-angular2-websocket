import {Component} from 'angular2/core';
import {StompWebsocket} from './stomp-websocket';
import {Message} from './message';

declare var SockJS: any;
declare var Stomp: any;

@Component({
    selector: 'websocket-messager',
    template: `
    <ul>
      <li *ngFor="#message of messages">
        {{message.content}}
      </li>
    </ul>

    <input id="content"
           required
           [(ngModel)]="newMessage.content"
           type="text">
   <button (click)="send()">Send</button>
    `
})
export class WebsocketMessager {
    newMessage: Message;
    messages: Message[] = [];
    initialMessages = [
        {content: 'Awesome communication'},
        {content: 'Seriously ? Angular 2 + WS over Stomp ?'},
        {content: 'Dockerize all of it buddy'}
    ];
    socket: StompWebsocket = new StompWebsocket();

    constructor() {
        this.socket.client = new SockJS("/ideas");
        console.log(this.socket.client);
        this.socket.stomp = Stomp.over(this.socket.client);
        this.socket.stomp.connect({}, function(frame) {
            console.log(frame);
        });

        this.newMessage = {content: ''};
        this.initialMessages.forEach((message) => this.messages.push(message));
    }

    send() {
        this.messages.push({content: this.newMessage.content});

        this.newMessage.content = '';
    }
}