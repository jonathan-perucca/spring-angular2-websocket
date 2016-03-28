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
    socket: StompWebsocket = new StompWebsocket();

    constructor() {
        this.socket.client = new SockJS("/ideas");
        client = Stomp.over(this.socket.client);

        let pushMessageLambda = (message) => {
            var parsedBody = JSON.parse(message.body);

            if(!Array.isArray(parsedBody)) {
                parsedBody = [parsedBody];
            }

            this.pushMessages(parsedBody);
        };
        client.connect({}, function(frame) {
            console.log('Frame: ' + frame);

            client.subscribe("/app/conversations", pushMessageLambda);
            client.subscribe("/topic/conversations", pushMessageLambda);
        });

        this.socket.stomp = client;
        this.newMessage = {content: ''};
    }

    public pushMessages(messages: Message[]) {
        messages.forEach((m) => this.messages.push(m));
    }

    public send() {
        this.socket.stomp.send("/app/conversations", {}, JSON.stringify({ content: this.newMessage.content }));

        this.newMessage.content = '';
    }
}