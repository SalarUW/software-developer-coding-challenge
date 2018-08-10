import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';


class App extends Component {
  emptyBid = {
    carName: '',
    username: '',
    amount: 0,
    car: ''
  };

  constructor(props) {
    super(props);
    this.state = {
      currentBid: this.emptyBid,
      isLoading: true,
      car: {}
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleBidSubmit = this.handleBidSubmit.bind(this);
  }


  async componentDidMount() {
    const response = await fetch ('/home');
    const body = await response.json();
    var currentBid = {...this.state.currentBid};
    currentBid.carName = body[0].name;
    this.setState((prevState, props) => {
        return {currentBid: currentBid, car: body[0], isLoading: false}
    });
  }


  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let currentBid = {...this.state.currentBid};
    currentBid[name] = value;
    this.setState((prevState, props) => {
        return {currentBid: currentBid};
    });
  }

  async handleBidSubmit(event) {
    event.preventDefault();
    const {currentBid} = this.state;

    await fetch('/home', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(currentBid),
    });

    const response = await fetch('/home');
    const body = await response.json();
    this.setState((prevState, props) => {
              return {car: body[0], isLoading: false};
    });
  }


  render() {
    const {car, isLoading, currentBid} = this.state;
    const bids = car.bids;
    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">{car.name}</h1>
        </header>
        <div>
          <Container>
            Make A New Bid!
            <Form onSubmit={this.handleBidSubmit}>
              <FormGroup>
                <Label for="username">Username</Label>
                <Input type="text" name="username" id="username" value={currentBid.username || ''} onChange={this.handleChange} autoComplete="name"/>
              </FormGroup>
              <FormGroup>
                <Label for="Amount">Amount</Label>
                <Input type="number" name="amount" id="amount" value={currentBid.amount || ''} onChange={this.handleChange}/>
              </FormGroup>
              <FormGroup>
                <Button color="primary" type="submit">Save</Button>{' '}
              </FormGroup>
            </Form>
          </Container>

          <br/>


          <h2>Current Winning Bid: ${car.highestBid}</h2>
          <h3>Bid History</h3>
          <ol>
          {bids.map(bid =>
            <li key={bid.id}>
            {bid.username} bid ${bid.amount}
            </li>
          )}
          </ol>
        </div>

      </div>
    );
  }
}

export default App;
