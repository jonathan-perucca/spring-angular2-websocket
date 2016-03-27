import {Component} from 'angular2/core';
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
    newMessage: Message = {content: ''};
    messages: Message[] = [
        {content: 'Awesome communication'},
        {content: 'Seriously ? Angular 2 + WS over Stomp ?'},
        {content: 'Dockerize all of it buddy'}
    ];

    send() {
        this.messages.push({content: this.newMessage.content});

        this.newMessage.content = '';
    }
}