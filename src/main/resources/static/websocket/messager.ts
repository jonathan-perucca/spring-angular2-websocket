import {Component} from 'angular2/core';
import {StompWebsocket} from './stomp-websocket';
import {Message} from './message';


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
    socket: StompWebsocket;

    constructor() {
        this.socket = new StompWebsocket("/ideas");

        let pushMessageLambda = (message) => {
            var parsedBody = JSON.parse(message.body);

            if(!Array.isArray(parsedBody)) {
                parsedBody = [parsedBody];
            }

            this.pushMessages(parsedBody);
        };
        this.socket.subscribe("/app/conversations", pushMessageLambda);
        this.socket.subscribe("/topic/conversations", pushMessageLambda);
        this.socket.listen();

        this.newMessage = {content: ''};
    }

    public pushMessages(messages: Message[]) {
        messages.forEach((m) => this.messages.push(m));
    }

    public send() {
        this.socket.send("/app/conversations", {}, JSON.stringify({ content: this.newMessage.content }));

        this.newMessage.content = '';
    }
}