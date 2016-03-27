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
        client = Stomp.over(this.socket.client);
        this.socket.stomp = client;

        let pushMessageLambda = (message) => this.pushMessage({content: message.body});

        client.connect({}, function(frame) {
            console.log(frame);

            client.subscribe("/topic/conversations", pushMessageLambda)
        });

        this.newMessage = {content: ''};
        this.initialMessages.forEach((message) => this.messages.push(message));
    }

    public pushMessage(message: Message) {
        this.messages.push(message);
    }

    public send() {
        this.socket.stomp.send("/app/conversations", {}, JSON.stringify({ content: this.newMessage.content }));

        this.newMessage.content = '';
    }
}