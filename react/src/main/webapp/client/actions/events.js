
export function listenForEvents(username) {
    return dispatch => {
        const ws = new WebSocket(`ws://localhost:8080/ws/events?username=${username}`);
        ws.onmessage = function(event) {
            const msg = JSON.parse(event.data);
            console.log(msg);
        };
    };
}
