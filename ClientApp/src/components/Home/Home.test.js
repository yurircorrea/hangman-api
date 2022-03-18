// React
import React from 'react';

// RTL
import { render } from '@testing-library/react';

// Components
import Home from './Home';

const renderComponent = () => render(<Home />);

describe('Home', () => {
  test('Should renders the component', () => {
    const { container } = renderComponent();
    expect(container).toBeDefined();
  });
});
