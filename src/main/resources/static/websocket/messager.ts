import {Component} from 'angular2/core';

@Component({
    selector: 'websocket-messager',
    templateUrl: 'app/view.html'
})
export class WebsocketMessager {
    messages: Message[] = [
        {content: 'Awesome communication'},
        {content: 'Seriously ? Angular 2 + WS over Stomp ?'},
        {content: 'Dockerize all of it buddy'},
    ];


}