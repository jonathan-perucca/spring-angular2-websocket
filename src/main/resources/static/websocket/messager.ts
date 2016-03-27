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
    `
})
export class WebsocketMessager {
    messages: Message[] = [
        {content : 'Awesome communication'},
        {content : 'Seriously ? Angular 2 + WS over Stomp ?'},
        {content : 'Dockerize all of it buddy'}
    ];
}