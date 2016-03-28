declare var SockJS: any;
declare var Stomp: any;

export class Channel {
    channelPath: string;
    callback: (message) => any;
}

export class StompWebsocket {
    private _stompTemplate: Stomp;
    private _endpoint: string;
    private _channels: Channel[];

    constructor(endpoint: string) {
        this._endpoint = endpoint;
        this._channels = [];
    }

    public subscribe(messageChannel: string, callback: (message) => any): void {
        this._channels.push({channelPath: messageChannel, callback});
    }

    public listen() {
        this._stompTemplate = Stomp.over(new SockJS(this._endpoint));
        let thisChannels = this._channels;
        let stompTemplate = this._stompTemplate;

        this._stompTemplate.connect({}, function(frame) {
            console.log('Frame: ' + frame);

            thisChannels.forEach((channel) => {
                stompTemplate.subscribe(channel.channelPath, channel.callback)
            });
        });
    }

    public send(endpoint: string, headers: any, body: any) {
        if(this._stompTemplate.connected) {
            this._stompTemplate.send(endpoint, headers, body);
        }
    }
}